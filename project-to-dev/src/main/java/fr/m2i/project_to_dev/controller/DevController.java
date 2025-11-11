package fr.m2i.project_to_dev.controller;

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

import fr.m2i.project_to_dev.controller.dto.DevBasicDTO;
import fr.m2i.project_to_dev.controller.dto.DevDetailDTO;
import fr.m2i.project_to_dev.dto.mapper.DevMapper;
import fr.m2i.project_to_dev.entity.Dev;
import fr.m2i.project_to_dev.repository.DevRepository;

@RestController
@RequestMapping("api/Dev")
public class DevController {

    private DevRepository repo;
    private DevMapper mapper;

    public DevController(DevRepository repo, DevMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    // _______________ CREATE _________________
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DevDetailDTO createDev(@RequestBody DevDetailDTO dto) {
        Dev newDev = mapper.toEntity(dto);
        repo.save(newDev);
        return mapper.toDto(newDev);
    }

    // ______________1 dev par ID_________________
    @GetMapping("/{id}")
    public DevDetailDTO getDevById(@PathVariable Integer id) {
        Dev dev = getOrNotFound(id);
        return mapper.toDto(dev);
    }

    // ______________une liste de dev_________________
    @GetMapping
    public List<DevBasicDTO> getAllDevs() {
        List<DevBasicDTO> devs = repo.findAll()
                .stream()
                .map(mapper::toListDto)
                .collect(Collectors.toList());
        return devs;
    }

    // _______________ UPDATE _________________
    @PutMapping("/{id}")
    public DevDetailDTO updateDev(@PathVariable Integer id, @RequestBody DevDetailDTO dto) {
        // recupe l'id dans l'url et les info dans le corps de la requete
        Dev existDev = getOrNotFound(id);// on choppe le dev existant
        Dev UpdatDev = mapper.toEntity(dto);// on convertit les infos en entité

        UpdatDev.setId(existDev.getId());// On remet l’ID de l’objet existant
        UpdatDev.setCandidacies(existDev.getCandidacies());// On garde les candidatures déjà liées au Dev.

        return mapper.toDto(repo.save(UpdatDev));
    }

    // _______________ DELETE _________________
      @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDev(@PathVariable Integer id) {
        Dev dev = getOrNotFound(id);
        repo.delete(dev);
    }



    //_____________________SPECIALS____________

    // _______________ Par exp _________________
    @GetMapping("/experience/{years}")
    public List<DevBasicDTO> getDevsByExperience(@PathVariable Integer years) {
        List<DevBasicDTO> devs = repo.findByExperienceGreaterThanEqual(years)
                .stream()
                .map(mapper::toListDto)
                .collect(Collectors.toList());
        return devs;
    }

    // _______________ par skill _________________
    @GetMapping("/skills")// exemple GET http://localhost:8080/api/Dev/skills?skills=Java&skills=Spring&skills=SQL
    public List<DevBasicDTO> getDevsBySkills(@RequestParam List<String> skills) {
        List<DevBasicDTO> devs = repo.findBySkills(skills)
                .stream()
                .map(mapper::toListDto)
                .collect(Collectors.toList());
        return(devs);
    }




    // ______________UTILITIES_________________
    private Dev getOrNotFound(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dev not found"));
    }
}
