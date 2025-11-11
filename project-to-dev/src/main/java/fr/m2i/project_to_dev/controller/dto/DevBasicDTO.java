package fr.m2i.project_to_dev.controller.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class DevBasicDTO {
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private List<String> skills;
    @NotBlank
    private Integer experience;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }
}
