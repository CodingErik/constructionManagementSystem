package com.company.constructionmanagementsystem.repository;

import com.company.constructionmanagementsystem.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findAllTasksByProjectId(Integer projectId);
    List<Task> findAllTasksByEmployeeId(Integer employeeId);
    List<Task> findAllTasksByProjectIdAndEmployeeId(Integer projectId, Integer employeeId);
    List<Task> findAllTasksByProjectIdAndStatus(Integer projectId, String status);
    Task findTasksByProjectIdAndName(Integer projectId, String name);
}
