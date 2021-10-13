package com.company.constructionmanagementsystem.repository;


import com.company.constructionmanagementsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByTitle(String title); // Test pass
    List<Employee> findByProjectId(Integer projectId); // Test pass
    List<Employee> findByName(String name); // Test pass
    Optional<Employee> findByEmail(String email); // Test pass
    Optional<Employee> findByUsername(String username); // Test pass
}
