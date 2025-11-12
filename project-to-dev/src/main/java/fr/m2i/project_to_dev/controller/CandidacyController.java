package fr.m2i.project_to_dev.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.m2i.project_to_dev.controller.dto.CandidacyDTO;
import fr.m2i.project_to_dev.dto.mapper.CandidacyMapper;
import fr.m2i.project_to_dev.entity.Candidacy;
import fr.m2i.project_to_dev.entity.CandidacyStatus;
import fr.m2i.project_to_dev.entity.Dev;
import fr.m2i.project_to_dev.entity.Project;
import fr.m2i.project_to_dev.repository.CandidacyRepository;
import fr.m2i.project_to_dev.repository.DevRepository;
import fr.m2i.project_to_dev.repository.ProjectRepository;

@RestController
@RequestMapping("/api/candidacies")
public class CandidacyController {

    private final CandidacyRepository repo;
    private final DevRepository devRepo;
    private final ProjectRepository projectRepo;
    private final CandidacyMapper mapper;

    public CandidacyController(
        CandidacyRepository repo,
        DevRepository devRepo,
        ProjectRepository projectRepo,
        CandidacyMapper mapper
    ) {
        this.repo = repo;
        this.devRepo = devRepo;
        this.projectRepo = projectRepo;
        this.mapper = mapper;
    }

    // __________________ CREATE __________________
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CandidacyDTO createCandidacy(@RequestBody CandidacyDTO dto) {
        // Vérifie que le Dev existe
        Dev dev = devRepo.findById(dto.getDevId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dev not found"));
        
        // Vérifie que le Project existe
        Project project = projectRepo.findById(dto.getProjectId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
        
        // Convertit le DTO en entité
        Candidacy candidacy = mapper.toEntity(dto);
        candidacy.setDev(dev);
        candidacy.setProject(project);
        candidacy.setSubmitDate(java.time.LocalDateTime.now()); // ajoute la date actuelle
        
        return mapper.toDto(repo.save(candidacy));
    }

    // __________________ READ ALL __________________
    @GetMapping
    public List<CandidacyDTO> getAllCandidacies() {
        return repo.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
// _______________ UPDATE STATUS _________________
@PatchMapping("/{id}/status")
public CandidacyDTO updateCandidacyStatus(
        @PathVariable Integer id,
        @RequestParam String status) {

    Candidacy candidacy = getOrNotFound(id);

    // Conversion de la String vers l'enum
    try {
        CandidacyStatus newStatus = CandidacyStatus.valueOf(status.toUpperCase());
        candidacy.setStatus(newStatus);
    } catch (IllegalArgumentException e) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid status value");
    }

    return mapper.toDto(repo.save(candidacy));
}
    // __________________ READ BY ID __________________
    @GetMapping("/{id}")
    public CandidacyDTO getCandidacyById(@PathVariable Integer id) {
        Candidacy candidacy = getOrNotFound(id);
        return mapper.toDto(candidacy);
    }

    // __________________ DELETE __________________
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCandidacy(@PathVariable Integer id) {
        Candidacy candidacy = getOrNotFound(id);
        repo.delete(candidacy);
    }

    // __________________ BY PROJECT __________________
    @GetMapping("/project/{projectId}")
    public List<CandidacyDTO> getCandidaciesByProject(@PathVariable Integer projectId) {
        return repo.findByProjectId(projectId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    // __________________ BY DEV __________________
    @GetMapping("/dev/{devId}")
    public List<CandidacyDTO> getCandidaciesByDev(@PathVariable Integer devId) {
        return repo.findByDevId(devId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    // __________________ BY STATUS __________________
    @GetMapping("/status")
    public List<CandidacyDTO> getCandidaciesByStatus(@RequestParam String status) {
        return repo.findByStatus(status)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    // __________________ UTIL __________________
    private Candidacy getOrNotFound(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidacy not found"));
    }
}
