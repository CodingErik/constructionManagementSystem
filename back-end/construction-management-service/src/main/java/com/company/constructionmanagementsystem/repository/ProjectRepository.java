package com.company.constructionmanagementsystem.repository;


import com.company.constructionmanagementsystem.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{

    List<Project> findByStatus(String status); // Test pass

    List<Project> findByDeadline(LocalDate deadline); // Test pass

    List<Project> findByStartDate(LocalDate startDate); // Test pass

    List<Project> findByRoomType(String roomType);

    List<Project> findByName(String name);

    List<Project> findByRoomTypeAndName(String roomType, String name);

}
