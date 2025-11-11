package fr.m2i.project_to_dev.controller.dto;

import jakarta.validation.constraints.NotBlank;

public class ProjectOwnerBasicDTO {
    private Integer id;
    @NotBlank
    private String name;
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

 
}
