package com.company.constructionmanagementsystem.repository;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class TaskRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TaskRepository taskRepository;


    @Before
    public void setUp() throws Exception {

        employeeRepository.deleteAll();

        projectRepository.deleteAll();

        taskRepository.deleteAll();
    }
}