package com.company.constructionmanagementsystem.controller;

import com.company.constructionmanagementsystem.model.Employee;
import com.company.constructionmanagementsystem.repository.EmployeeRepository;
import com.company.constructionmanagementsystem.service.EmployeeServiceLayer;
import com.company.constructionmanagementsystem.util.LoginBody;
import com.company.constructionmanagementsystem.viewmodel.EmployeeViewModel;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@CrossOrigin
@RestController
//@RefreshScope
public class EmployeeController {

    @Autowired
    EmployeeRepository repository;

    @Autowired
    EmployeeServiceLayer employeeServiceLayer;

    @RequestMapping(value="/construction", method = RequestMethod.GET)
    public String helloCloud() {

        return "construction service working";
    }

    @GetMapping("/api/employees")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = repository.findAll();

        return employeeList;
    }


    @GetMapping("/api/employees/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public EmployeeViewModel getEmployeeById(@PathVariable Integer id) throws Exception {
        EmployeeViewModel returnEmployee = employeeServiceLayer.findEmployeeById(id);

        return returnEmployee;
    }

    @GetMapping("/api/employees/findByEmail/{email}")
    @ResponseStatus(value = HttpStatus.OK)
    public EmployeeViewModel getEmployeeByEmail(@PathVariable String email) throws Exception {
        EmployeeViewModel returnEmployee = employeeServiceLayer.findEmployeeByEmail(email);

        return returnEmployee;

    }

    @GetMapping("/api/employees/findByName/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    public EmployeeViewModel getEmployeeByName(@PathVariable String name) throws Exception {
        EmployeeViewModel returnEmployee = employeeServiceLayer.findEmployeeByName(name);

        return returnEmployee;
    }

    @GetMapping("/api/employees/findByUsername/{username}")
    @ResponseStatus(value = HttpStatus.OK)
    public EmployeeViewModel getEmployeeByUsername(@PathVariable @Valid String username) throws Exception {
        EmployeeViewModel returnEmployee = employeeServiceLayer.findEmployeeByUsername(username);

        return returnEmployee;
    }

    @GetMapping("/api/employees/findByTitle/{title}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<EmployeeViewModel> getEmployeesByTitle(@PathVariable String title) throws Exception {
        List<EmployeeViewModel> employeeList = employeeServiceLayer.findEmployeesByTitle(title);

        return employeeList;
    }

    @GetMapping("/api/employees/findByProjectId/{projectId}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<EmployeeViewModel> getEmployeesByProjectId(@PathVariable Integer projectId) throws Exception {
        List<EmployeeViewModel> employeeList = employeeServiceLayer.findEmployeesByProjectId(projectId);

        return employeeList;
    }

    /**
     * register endpoint
     */
    @PostMapping("/api/employees/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Employee addNewEmployee(@RequestBody @Valid Employee employee) throws Exception {


        if (employee.getPassword() == null) {
            employee.setPassword("defaultPass");
        } else {
            employee.setPassword(BCrypt.hashpw(employee.getPassword(), BCrypt.gensalt()));
        }

        employee.setUsername(employee.getUsername().toLowerCase(Locale.ROOT));
        employee.setProjectId(0);
        employee.setName(employee.getName().toLowerCase(Locale.ROOT));
        employee.setEmail(employee.getEmail().toLowerCase(Locale.ROOT));
        employee.setUserSince(LocalDate.now());
        employee.setYearsOfExperience(0);

        employee = repository.save(employee);
        System.out.println(employee);
        return employee;
    }

    /**
     * login endpoint
     */
    @PostMapping("/api/employees/login")
    @ResponseStatus(value = HttpStatus.OK)
    public Employee login(@RequestBody LoginBody login) throws Exception {

        if (!repository.findByUsername(login.getUsername().toLowerCase(Locale.ROOT)).isPresent()) {
            throw new IllegalArgumentException("Username not found.");
        }
        Employee employee = repository.findByUsername(login.getUsername().toLowerCase(Locale.ROOT)).get();

        BCryptPasswordEncoder bce = new BCryptPasswordEncoder();
        if (!bce.matches(login.getPassword(), employee.getPassword())) {
            throw new IllegalArgumentException("Password not match");
        }

        return employee;
    }

    @PutMapping("/api/employees")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmployee(@RequestBody Employee employee) throws Exception {
        repository.save(employee);
    }

}
