package fr.m2i.project_to_dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.m2i.project_to_dev.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{



}
