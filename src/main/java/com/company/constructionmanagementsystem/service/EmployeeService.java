package com.company.constructionmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<EmployeeViewModel> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public EmployeeViewModel createEmployee(EmployeeViewModel employeeViewModel) {

    }

}
