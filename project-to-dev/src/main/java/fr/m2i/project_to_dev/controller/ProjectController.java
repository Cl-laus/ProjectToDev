package fr.m2i.project_to_dev.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.m2i.project_to_dev.controller.dto.ProjectDetailDTO;
import fr.m2i.project_to_dev.dto.mapper.ProjectMapper;
import fr.m2i.project_to_dev.entity.Project;
import fr.m2i.project_to_dev.entity.ProjectOwner;
import fr.m2i.project_to_dev.repository.ProjectOwnerRepository;
import fr.m2i.project_to_dev.repository.ProjectRepository;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectRepository repo;
    private final ProjectOwnerRepository ownerRepo;
    private final ProjectMapper mapper;

    public ProjectController(ProjectRepository repo, ProjectOwnerRepository ownerRepo, ProjectMapper mapper) {
        this.repo = repo;
        this.ownerRepo = ownerRepo;
        this.mapper = mapper;
    }

    // _______________ CREATE _________________
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDetailDTO createProject(@RequestBody ProjectDetailDTO dto) {
        Project project = mapper.toEntity(dto);

        ProjectOwner owner = ownerRepo.findById(dto.getOwnerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner not found"));
        // on verifie que l'owner est dans la bdd via l'id present dans le body

        project.setOwner(owner);// on le lie au projet

        return mapper.toDto(repo.save(project));
    }

    // _______________ READ ALL _________________
    @GetMapping
    public List<ProjectDetailDTO> getAllProjects() {
        List<ProjectDetailDTO> projects = repo.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return projects;
    }

    // _______________ READ BY ID _________________
    @GetMapping("/{id}")
    public ProjectDetailDTO getProjectById(@PathVariable Integer id) {
        Project project = getOrNotFound(id);
        return mapper.toDto(project);
    }

    // _______________ UPDATE _________________
    @PutMapping("/{id}")
    public ProjectDetailDTO updateProject(@PathVariable Integer id, @RequestBody ProjectDetailDTO dto) {
        // Récupère le projet existant
        Project existProject = getOrNotFound(id);

        // Convertit le DTO en entité
        Project updateProject = mapper.toEntity(dto);

        // On conserve l'ID et les candidatures existantes
        updateProject.setId(existProject.getId());
        updateProject.setCandidacies(existProject.getCandidacies());

        // On récupère le propriétaire et on le lie au projet
        ProjectOwner owner = ownerRepo.findById(dto.getOwnerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner not found"));
        updateProject.setOwner(owner);

        // Sauvegarde et retourne le projet mis à jour
        return mapper.toDto(repo.save(updateProject));
    }

    // _______________ DELETE _________________
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable Integer id) {
        Project project = getOrNotFound(id);
        repo.delete(project);
    }

    // __________________SPECIALS___________________

    // _______________ SEARCH : BUDGET _________________
    @GetMapping("/budget")
    public List<ProjectDetailDTO> getProjectsByBudgetRange(@RequestParam Float min, @RequestParam Float max) {
        return repo.findByBudgetBetween(min, max)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    // _______________ SEARCH : THEME _________________
    @GetMapping("/theme")
    public List<ProjectDetailDTO> getProjectsByTheme(@RequestParam String theme) {
        return repo.findByThemeContaining(theme)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    // _______________ SEARCH : DELIVERY DATE _________________
    @GetMapping("/after")
    public List<ProjectDetailDTO> getProjectsByDeliveryDateAfter(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return repo.findByDeliveryDateAfter(Date.valueOf(localDate))
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    // _______________ UTIL _________________
    private Project getOrNotFound(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
    }
}
