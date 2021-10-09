package com.company.constructionmanagementsystem.repository;


import com.company.constructionmanagementsystem.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{

    List<Project> findByDeadline(LocalDate deadline);

}
