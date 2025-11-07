package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "owner_id", nullable = false)
    @ManyToOne
    private ProjectOwner owner;

    @Column(name = "candidacies_id", nullable = false)
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Candidacy> candidacies = new ArrayList<>();

    @Column(nullable = false)
    private String name;
    @Lob
    private String description;
    @Column(nullable = false)
    private String theme;

    @Column(nullable = false)
    private LocalDate deliveryDate;

    private Integer budget;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProjectOwner getOwner() {
        return owner;
    }

    public void setOwner(ProjectOwner owner) {
        this.owner = owner;
    }

    public List<Candidacy> getCandidacies() {
        return candidacies;
    }

    public void setCandidacies(List<Candidacy> candidacies) {
        this.candidacies = candidacies;
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

}
