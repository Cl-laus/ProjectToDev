package entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;


@Entity
public class Dev extends User {

    private List<String> skills;
    private Integer experience;
@Column(name = "candidacies_id", nullable = false)
    @OneToMany(mappedBy = "dev", cascade = CascadeType.ALL)
    private List<Candidacy> candidacies = new ArrayList<>();

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

    public List<Candidacy> getCandidacies() {
        return candidacies;
    }

    public void setCandidacies(List<Candidacy> candidacies) {
        this.candidacies = candidacies;
    }

}
