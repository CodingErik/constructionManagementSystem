package com.company.constructionmanagementsystem.repository;

import com.company.constructionmanagementsystem.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findAllTasksByName(String name); // Test pass

    List<Task> findAllTasksByProjectId(Integer projectId); // Test pass
    List<Task> findAllTasksByEmployeeId(Integer employeeId); // Test pass
    List<Task> findAllTasksByProjectIdAndEmployeeId(Integer projectId, Integer employeeId); // Test pass

//    List<Task> findAllTasksByProjectIdAndStatus(Integer projectId, String status);
    List<Task> findAllTasksByProjectIdAndName(Integer projectId, String name); // Test pass
    List<Task> findAllTasksByEmployeeIdAndName(Integer employeeId, String name); // Test pass
    List<Task> findAllTasksByProjectIdAndEmployeeIdAndName(Integer projectId,Integer employeeId,String name);


}
