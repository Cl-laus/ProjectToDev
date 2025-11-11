package fr.m2i.project_to_dev.controller.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

public class CandidacyDTO {

    private Integer id;
    private Integer devId;
    private Integer projectId;
    private LocalDateTime submitDate;
    @NotBlank
    private String status; //'l'enum contient des strings


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getDevId() {
        return devId;
    }
    public void setDevId(Integer devId) {
        this.devId = devId;
    }
    public Integer getProjectId() {
        return projectId;
    }
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
    public LocalDateTime getSubmitDate() {
        return submitDate;
    }
    public void setSubmitDate(LocalDateTime submitDate) {
        this.submitDate = submitDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
