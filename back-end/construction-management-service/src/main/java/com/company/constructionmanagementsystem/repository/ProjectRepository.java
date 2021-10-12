package com.company.constructionmanagementsystem.repository;


import com.company.constructionmanagementsystem.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{

    List<Project> findByName(String name);

    List<Project> findByDeadline(LocalDate deadline);
    List<Project> findByStartDate(LocalDate startDate);
    List<Project> findByStatus(String status);

    List<Project> findByRoomType(String roomType);

    List<Project> findByRoomTypeAndName(String roomType, String name);
    List<Project> findByRoomTypeAndNameAndIsPlumbing(String roomType, String name, Boolean isPlumbing);
    List<Project> findByRoomTypeAndNameAndIsElectric(String roomType, String name, Boolean isElectric);

    List<Project> findByIsPlumbing(Boolean isPlumbing);
    List<Project> findByIsElectric(Boolean isElectric);
    List<Project> findByIsPlumbingAndIsElectric(Boolean isPlumbing, Boolean isElectric);

    List<Project> findByRoomTypeAndIsPlumbing(String roomType, Boolean isPlumbing);
    List<Project> findByRoomTypeAndIsElectric(String roomType, Boolean isElectric);
    List<Project> findByNameAndIsPlumbing(String name, Boolean isPlumbing);
    List<Project> findByNameAndIsElectric(String name, Boolean isElectric);

    List<Project> findByRoomTypeAndIsPlumbingAndIsElectric(String roomType, Boolean isPlumbing, Boolean isElectric);
    List<Project> findByNameAndIsPlumbingAndIsElectric(String name, Boolean isPlumbing, Boolean isElectric);

    List<Project> findByNameAndRoomTypeAndIsPlumbingAndIsElectric(String name, String roomType, Boolean isPlumbing, Boolean isElectric);
}
