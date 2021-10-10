package com.company.constructionmanagementsystem.repository;


import com.company.constructionmanagementsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByTitle(String title);
    List<Employee> findByProjectId(Integer projectId);
    Optional<Employee> findByEmail(String email);
    Optional<Employee> findByName(String name);
    Optional<Employee> findByUsername(String username);
}
