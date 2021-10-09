package com.company.constructionmanagementsystem.controller;

import com.company.constructionmanagementsystem.model.Employee;
import com.company.constructionmanagementsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository repository;

    @GetMapping("/api/employees")
    public List<Employee> getAllEmployees(){
        List<Employee> employeeList = repository.findAll();

        return employeeList;
    }

    @GetMapping("/api/employees/findByEmail/{email}")
    public Optional<Employee> getEmployeeByEmail(@PathVariable String email) throws Exception{
        Optional<Employee> foundEmployee = repository.findByEmail(email);

        if(foundEmployee.isPresent()){
            return foundEmployee;
        } else {
            throw new FileNotFoundException("No employee found with email " + email);
        }

    }

    @GetMapping("/api/employees/findByName/{name}")
    public Optional<Employee> getEmployeeByName(@PathVariable String name) throws Exception{

        Optional<Employee> foundEmployee = repository.findByName(name);

        if(foundEmployee.isPresent()){
            return foundEmployee;
        } else {
            throw new FileNotFoundException("No employee found with name " + name);
        }

    }

    @GetMapping("/api/employees/findByUsername/{username}")
    public Optional<Employee> getEmployeeByUsername(@PathVariable String username) throws Exception{
        Optional<Employee> foundEmployee = repository.findByUsername(username);

        if(foundEmployee.isPresent()){
            return foundEmployee;
        } else {
            throw new FileNotFoundException("No employee found with username " + username);
        }
    }

    @GetMapping("/api/employees/findByTitle/{title}")
    public List<Employee> getEmployeesByTitle(@PathVariable String title) throws Exception{
        List<Employee> employeeList = repository.findByTitle(title);

        return employeeList;
    }

    @GetMapping("/api/employees/findByProjectId/{projectId}")
    public List<Employee> getEmployeesByProjectId(@PathVariable Integer projectId) throws Exception{
        List<Employee> employeeList = repository.findByProjectId(projectId);

        return employeeList;
    }

    @PostMapping("/api/employees")
    public Employee addNewEmployee(@RequestBody Employee employee){
        employee.setPassword(BCrypt.hashpw(employee.getPassword(), BCrypt.gensalt()));
        repository.save(employee);
        return employee;
    }

}
