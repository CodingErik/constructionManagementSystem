package com.company.constructionmanagementsystem.repository;

import com.company.constructionmanagementsystem.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByProjectId(Integer projectId);
    List<Task> findByEmployeeId(Integer employeeId);
    List<Task> findByProjectIdAndEmployeeId(Integer projectId, Integer employeeId);
}
