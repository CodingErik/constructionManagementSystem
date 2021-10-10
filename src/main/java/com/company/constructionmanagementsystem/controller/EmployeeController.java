package com.company.constructionmanagementsystem.controller;

import com.company.constructionmanagementsystem.exceptions.NotFoundException;
import com.company.constructionmanagementsystem.model.Employee;
import com.company.constructionmanagementsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository repository;

    @GetMapping("/api/employees")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Employee> getAllEmployees(){
        List<Employee> employeeList = repository.findAll();

        return employeeList;
    }


    @GetMapping("/api/employees/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Optional<Employee> getEmployeeById(@PathVariable Integer id) throws Exception{
        Optional<Employee> foundEmployee = repository.findById(id);

        if(foundEmployee.isPresent()){
            return foundEmployee;
        } else {
            throw new NotFoundException("No Employee with id " + id);
        }
    }

    @GetMapping("/api/employees/findByEmail/{email}")
    @ResponseStatus(value = HttpStatus.OK)
    public Optional<Employee> getEmployeeByEmail(@PathVariable String email) throws Exception{
        Optional<Employee> foundEmployee = repository.findByEmail(email);

        if(foundEmployee.isPresent()){
            return foundEmployee;
        } else {
            throw new NotFoundException("No employee found with email " + email);
        }

    }

    @GetMapping("/api/employees/findByName/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    public Optional<Employee> getEmployeeByName(@PathVariable String name) throws Exception{

        Optional<Employee> foundEmployee = repository.findByName(name);

        if(foundEmployee.isPresent()){
            return foundEmployee;
        } else {
            throw new NotFoundException("No employee found with name " + name);
        }

    }

    @GetMapping("/api/employees/findByUsername/{username}")
    @ResponseStatus(value = HttpStatus.OK)
    public Optional<Employee> getEmployeeByUsername(@PathVariable @Valid String username) throws Exception{
        Optional<Employee> foundEmployee = repository.findByUsername(username);

        if(foundEmployee.isPresent()){
            return foundEmployee;
        } else {
            throw new NotFoundException("Noe employee found with username "+username);
        }
    }

    @GetMapping("/api/employees/findByTitle/{title}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Employee> getEmployeesByTitle(@PathVariable String title) throws Exception{
        List<Employee> employeeList = repository.findByTitle(title);

        return employeeList;
    }

    @GetMapping("/api/employees/findByProjectId/{projectId}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Employee> getEmployeesByProjectId(@PathVariable Integer projectId) throws Exception{
        List<Employee> employeeList = repository.findByProjectId(projectId);

        return employeeList;
    }

    @PostMapping("/api/employees")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Employee addNewEmployee(@RequestBody @Valid Employee employee){

        if(employee.getPassword() == null){
            employee.setPassword("defaultPass");
        } else {
            employee.setPassword(BCrypt.hashpw(employee.getPassword(), BCrypt.gensalt()));
        }

        repository.save(employee);
        return employee;
    }

}
