package fr.m2i.project_to_dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.m2i.project_to_dev.entity.Project;
import fr.m2i.project_to_dev.entity.ProjectOwner;

import java.sql.Date;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> findByOwner(ProjectOwner owner);

    List<Project> findByOwnerId(Integer id);

    

    List<Project> findByBudgetBetween(Float minBudget, Float maxBudget);

    List<Project> findByThemeContaining(String theme);

    List<Project> findByDeliveryDateAfter(Date date);

}
