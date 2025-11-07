package fr.m2i.project_to_dev.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Candidacy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "dev_id", nullable = false)
    @ManyToOne
    private Dev dev;

    @JoinColumn(name = "project_id", nullable = false)
    @ManyToOne
    private Project project;

    @Column(name = "submit_date", nullable = false)
    private LocalDateTime submitDate;

    @Enumerated(EnumType.STRING)
    
    @Column(nullable = false, length = 20)
    private CandidacyStatus status = CandidacyStatus.PENDING;// lié au enum dans le fichier du meme nom
    // assigne une valeur par default

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Dev getDev() {
        return dev;
    }

    public void setDev(Dev dev) {
        this.dev = dev;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public LocalDateTime getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(LocalDateTime submitDate) {
        this.submitDate = submitDate;
    }

    public CandidacyStatus getStatus() {
        return status;
    }

    public void setStatus(CandidacyStatus status) {
        this.status = status;
    }
}
