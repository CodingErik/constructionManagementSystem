package com.company.constructionmanagementsystem.controller;

import com.company.constructionmanagementsystem.model.Employee;
import com.company.constructionmanagementsystem.repository.EmployeeRepository;
import com.company.constructionmanagementsystem.service.EmployeeServiceLayer;
import com.company.constructionmanagementsystem.viewmodel.EmployeeViewModel;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static java.lang.Integer.parseInt;

@CrossOrigin
@RestController
@RefreshScope
public class EmployeeController {

    @Autowired
    EmployeeRepository repository;

    @Autowired
    EmployeeServiceLayer employeeServiceLayer;

    @RequestMapping(value = "/construction", method = RequestMethod.GET)
    public String helloCloud() {

        return "construction service working";
    }

    @GetMapping("/api/employees")
    @ResponseStatus(value = HttpStatus.OK)
    public List<EmployeeViewModel> getAllEmployees() {
        List<EmployeeViewModel> employeeList = employeeServiceLayer.findAllEmployees();

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

        if (employee.getProjectId() == null) {
            employee.setProjectId(0);
        }

        employee.setUsername(employee.getUsername().toLowerCase(Locale.ROOT));
        employee.setName(employee.getName().toLowerCase(Locale.ROOT));
        employee.setTitle(employee.getTitle().toLowerCase(Locale.ROOT));
        employee.setEmail(employee.getEmail().toLowerCase(Locale.ROOT));
        employee.setYearsOfExperience(0);

        employee = repository.save(employee);
        System.out.println(employee);
        return employee;
    }


    @PutMapping("/api/employees")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmployee(@RequestBody Employee employee) throws Exception {
        repository.save(employee);
    }

    @DeleteMapping("/api/employees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Integer id) {
        employeeServiceLayer.deleteEmployee(id);
    }


    @PutMapping("/api/resetPassword")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void resetPassword (@RequestBody Map < String, String > inputJson) throws Exception {
        employeeServiceLayer.updateEmployeePassword(parseInt(inputJson.get("id")), inputJson.get("password"));
    }

}