package com.company.constructionmanagementsystem.controller;

import com.company.constructionmanagementsystem.model.Employee;
import com.company.constructionmanagementsystem.repository.EmployeeRepository;
import com.company.constructionmanagementsystem.repository.ProjectRepository;
import com.company.constructionmanagementsystem.repository.TaskRepository;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.junit.Assert.*;

public class EmployeeControllerTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TaskRepository taskRepository;

    Employee employee1;
    Employee employee2;
    Employee employee3;
    Employee employee4;
    Employee employee5;

    @Before
    public void setUp() throws Exception {
        java.time.LocalDate birth = java.time.LocalDate.of(1999,9 ,9);
        java.time.LocalDate since = java.time.LocalDate.now();
        MathContext mathContext = new MathContext(4);

        employee1 = new Employee(1,1,"Architect","Amal",birth,new BigDecimal(430.33).round(mathContext),4,"amalj2426@gmail.com","314-378-4297","amalj","password",since);
        employee2 = new Employee(2,1,"Worker","Hannah",birth,new BigDecimal(430.33).round(mathContext),2,"hannahb@gmail.com","422-987-2243","hannahb","password",since);
        employee3 = new Employee(3,2,"Architect","Nargiza",birth,new BigDecimal(430.33).round(mathContext),8,"nargiza@gmail.com","224-493-4288","narg","password",since);
        employee4 = new Employee(4,2,"Worker","Milana",birth,new BigDecimal(430.33).round(mathContext),1,"milana@gmail.com","734-902-2987","milan","password",since);
        employee5 = new Employee(5,2,"Worker","Tamila",birth,new BigDecimal(430.33).round(mathContext),1,"tamila@gmail.com","417-487-4227","tamil","password",since);
    }

    @Test
    public void shouldReturnAllEmployees() {

    }
}