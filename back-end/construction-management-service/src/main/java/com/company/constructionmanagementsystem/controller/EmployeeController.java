package com.company.constructionmanagementsystem.controller;

import com.company.constructionmanagementsystem.exceptions.NotFoundException;
import com.company.constructionmanagementsystem.model.Employee;
import com.company.constructionmanagementsystem.repository.EmployeeRepository;
import com.company.constructionmanagementsystem.service.EmployeeServiceLayer;
import com.company.constructionmanagementsystem.viewmodel.EmployeeViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository repository;

    @Autowired
    EmployeeServiceLayer employeeServiceLayer;

    @GetMapping("/api/employees")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Employee> getAllEmployees(){
        List<Employee> employeeList = repository.findAll();

        return employeeList;
    }


    @GetMapping("/api/employees/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public EmployeeViewModel getEmployeeById(@PathVariable Integer id) throws Exception{
        EmployeeViewModel returnEmployee = employeeServiceLayer.findEmployeeById(id);

        return returnEmployee;
    }

    @GetMapping("/api/employees/findByEmail/{email}")
    @ResponseStatus(value = HttpStatus.OK)
    public EmployeeViewModel getEmployeeByEmail(@PathVariable String email) throws Exception{
        EmployeeViewModel returnEmployee = employeeServiceLayer.findEmployeeByEmail(email);

        return returnEmployee;

    }

    @GetMapping("/api/employees/findByName/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    public EmployeeViewModel getEmployeeByName(@PathVariable String name) throws Exception{
        EmployeeViewModel returnEmployee = employeeServiceLayer.findEmployeeByName(name);

        return returnEmployee;
    }

    @GetMapping("/api/employees/findByUsername/{username}")
    @ResponseStatus(value = HttpStatus.OK)
    public EmployeeViewModel getEmployeeByUsername(@PathVariable @Valid String username) throws Exception{
        EmployeeViewModel returnEmployee = employeeServiceLayer.findEmployeeByUsername(username);

        return returnEmployee;
    }

    @GetMapping("/api/employees/findByTitle/{title}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<EmployeeViewModel> getEmployeesByTitle(@PathVariable String title) throws Exception{
        List<EmployeeViewModel> employeeList = employeeServiceLayer.findEmployeesByTitle(title);

        return employeeList;
    }

    @GetMapping("/api/employees/findByProjectId/{projectId}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<EmployeeViewModel> getEmployeesByProjectId(@PathVariable Integer projectId) throws Exception{
        List<EmployeeViewModel> employeeList = employeeServiceLayer.findEmployeesByProjectId(projectId);

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
