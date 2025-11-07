package fr.m2i.project_to_dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.m2i.project_to_dev.entity.Candidacy;

@Repository
public interface CandidacyRepository extends JpaRepository<Candidacy,Integer>{

    List<Candidacy> findByProjectId(Integer projectId);
    List<Candidacy> findByDevId(Integer devId);
    List<Candidacy> findByStatus(String status); // "pending", "accepted", "declined"
}
