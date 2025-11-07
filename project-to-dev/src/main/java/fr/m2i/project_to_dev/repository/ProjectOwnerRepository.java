package fr.m2i.project_to_dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.m2i.project_to_dev.entity.ProjectOwner;

@Repository
public interface ProjectOwnerRepository extends JpaRepository<ProjectOwner,Integer>{

    
   Optional<ProjectOwner> findByUserId (Integer userId);
   
}
