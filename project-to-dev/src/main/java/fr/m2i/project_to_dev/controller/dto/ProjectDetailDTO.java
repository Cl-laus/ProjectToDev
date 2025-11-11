package fr.m2i.project_to_dev.controller.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProjectDetailDTO {
 private Integer id;
 @NotBlank
    private String name;
    @Size(max = 500, message = "La description ne peut pas dépasser 500 caractères")
    private String description;
    private String theme;
    @NotBlank
    private LocalDate deliveryDate;
    @NotBlank
    private Integer budget;
    @NotBlank
    private Integer ownerId; 
    private List<Integer> candidaciesIds;
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getTheme() {
        return theme;
    }
    public void setTheme(String theme) {
        this.theme = theme;
    }
    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public Integer getBudget() {
        return budget;
    }
    public void setBudget(Integer budget) {
        this.budget = budget;
    }
    public Integer getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }
    public List<Integer> getCandidaciesIds() {
        return candidaciesIds;
    }
    public void setCandidaciesIds(List<Integer> candidaciesIds) {
        this.candidaciesIds = candidaciesIds;
    } 
}
