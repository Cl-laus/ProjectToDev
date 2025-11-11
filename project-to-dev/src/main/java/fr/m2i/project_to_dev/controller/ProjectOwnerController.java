package fr.m2i.project_to_dev.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import fr.m2i.project_to_dev.controller.dto.ProjectDetailDTO;
import fr.m2i.project_to_dev.controller.dto.ProjectOwnerBasicDTO;
import fr.m2i.project_to_dev.controller.dto.ProjectOwnerDetailDTO;
import fr.m2i.project_to_dev.dto.mapper.ProjectMapper;
import fr.m2i.project_to_dev.dto.mapper.ProjectOwnerMapper;
import fr.m2i.project_to_dev.entity.ProjectOwner;
import fr.m2i.project_to_dev.repository.ProjectOwnerRepository;

@RestController
@RequestMapping("/api/project-owner")
public class ProjectOwnerController {

    private final ProjectOwnerRepository repo;
    private final ProjectOwnerMapper mapper;
    private final ProjectMapper projectMapper;

    public ProjectOwnerController(ProjectOwnerRepository repo, ProjectOwnerMapper mapper, ProjectMapper projectMapper) {
        this.repo = repo;
        this.mapper = mapper;
        this.projectMapper = projectMapper;
    }

    // _______________ CREATE _________________
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectOwnerDetailDTO createOwner(@RequestBody ProjectOwnerDetailDTO dto) {
        ProjectOwner newOwner = mapper.toEntity(dto);
        repo.save(newOwner);
        return mapper.toDto(newOwner);
    }

    // _______________ UN LISTE  _________________
    @GetMapping
    public List<ProjectOwnerBasicDTO> getAllOwners() {
        List<ProjectOwnerBasicDTO> owners = repo.findAll()
                .stream()
                .map(mapper::toListDto)
                .collect(Collectors.toList());
        return owners;
    }

    // _______________ 1 PAR ID _________________
    @GetMapping("/{id}")
    public ProjectOwnerDetailDTO getOwnerById(@PathVariable Integer id) {
        ProjectOwner owner = getOrNotFound(id);
        return mapper.toDto(owner);
    }

    // _______________ UPDATE _________________
    @PutMapping("/{id}")
    public ProjectOwnerDetailDTO updateOwner(@PathVariable Integer id, @RequestBody ProjectOwnerDetailDTO dto) {
        ProjectOwner existing = getOrNotFound(id);
        ProjectOwner updated = mapper.toEntity(dto);

        updated.setId(existing.getId());
        updated.setProjects(existing.getProjects()); 

        return mapper.toDto(repo.save(updated));
    }

    // _______________ DELETE _________________
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOwner(@PathVariable Integer id) {
        ProjectOwner owner = getOrNotFound(id);
        repo.delete(owner);
    }
 // _______________ GET PROJECTS BY OWNER _________________
    @GetMapping("/{id}/projects")
    public List<ProjectDetailDTO> getProjectsByOwner(@PathVariable Integer id) {
        ProjectOwner owner = getOrNotFound(id);
        return owner.getProjects()
                    .stream()
                    .map(projectMapper::toDto)
                    .collect(Collectors.toList());
    }

    // _______________ UTILS _________________
    private ProjectOwner getOrNotFound(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ProjectOwner not found"));
    }
}
