package fr.m2i.project_to_dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.m2i.project_to_dev.entity.Dev;
import fr.m2i.project_to_dev.entity.ProjectOwner;
import java.util.List;


@Repository
public interface DevRepository extends JpaRepository<Dev, Integer> {
    Optional<ProjectOwner> findByUserId(Integer userId);
    List<Dev> findBySkills(List<String> skills);
    List<Dev> findByExperienceGreaterThanEqual(Integer years);
}
