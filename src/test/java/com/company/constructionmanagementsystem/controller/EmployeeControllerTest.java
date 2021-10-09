package com.company.constructionmanagementsystem.controller;

import com.company.constructionmanagementsystem.model.Employee;
import com.company.constructionmanagementsystem.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EmployeeRepository repository;

    private ObjectMapper mapper = new ObjectMapper();

    Employee employee1;
    Employee employee2;
    Employee employee3;
    Employee employee4;
    Employee employee5;
    Employee inputEmployee;

    @Before
    public void setUp() throws Exception {
        // Set up Employee1
        employee1 = new Employee();
        employee1.setId(1);
        employee1.setProjectId(1);
        employee1.setTitle("Architect");
        employee1.setName("Amal Janabayev");
        employee1.setSalary(new BigDecimal("40.000"));
        employee1.setYearsOfExperience(3);
        employee1.setEmail("amalj2426@gmail.com");
        employee1.setPhoneNumber("3143784296");
        employee1.setUsername("amalj24");
        // ------------------


        //Set up Employee2
        employee2 = new Employee();
        employee2.setId(2);
        employee2.setProjectId(1);
        employee2.setTitle("Worker");
        employee2.setName("Hannah Burson");
        employee2.setSalary(new BigDecimal("30.000"));
        employee2.setYearsOfExperience(1);
        employee2.setEmail("hannab@yahoo.com");
        employee2.setPhoneNumber("3341299809");
        employee2.setUsername("hannahbee");
        // --------------------


        //Set up Employee3
        employee3 = new Employee();
        employee3.setId(3);
        employee3.setProjectId(1);
        employee3.setTitle("Worker");
        employee3.setName("John Smith");
        employee3.setSalary(new BigDecimal("27.000"));
        employee3.setYearsOfExperience(2);
        employee3.setEmail("john@gmail.com");
        employee3.setPhoneNumber("2319873343");
        employee3.setUsername("johnSmith");
        // --------------------


        //Set up Employee4
        employee4 = new Employee();
        employee4.setId(4);
        employee4.setProjectId(2);
        employee4.setTitle("Architect");
        employee4.setName("Maria Botez");
        employee4.setSalary(new BigDecimal("50.000"));
        employee4.setYearsOfExperience(6);
        employee4.setEmail("maria@yahoo.com");
        employee4.setPhoneNumber("1435876643");
        employee4.setUsername("mariabotez");
        // --------------------

        //Set up Employee5
        employee5 = new Employee();
        employee5.setId(5);
        employee5.setProjectId(2);
        employee5.setTitle("Worker");
        employee5.setName("Jacob Galson");
        employee5.setSalary(new BigDecimal("30.000"));
        employee5.setYearsOfExperience(3);
        employee5.setEmail("jacob@yahoo.com");
        employee5.setPhoneNumber("2223143879");
        employee5.setUsername("jacobgalson");
        // --------------------


        // Set up Input Employee
        inputEmployee = new Employee();
        inputEmployee.setId(1);
        inputEmployee.setProjectId(5);
//        inputEmployee.setPassword("password");
        inputEmployee.setTitle("Architect");
        inputEmployee.setName("Amal Janabayev");
        inputEmployee.setSalary(new BigDecimal("40.000"));
        inputEmployee.setYearsOfExperience(3);
        inputEmployee.setEmail("amalj2426@gmail.com");
        inputEmployee.setPhoneNumber("3143784296");
        inputEmployee.setUsername("amalj24");
        // ------------------
    }

    @Test
    public void shouldReturnCorrectEmployeeWhenSearchedById() throws Exception{
        repository.deleteAll();


        Optional<Employee> optEmployee1 = Optional.of(employee1);
        given(repository.findById(employee1.getId())).willReturn(optEmployee1);
        String jsonOutput1 = mapper.writeValueAsString(employee1);

        mockMvc.perform(get("/api/employees/"+employee1.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonOutput1));

        Optional<Employee> optEmployee2 = Optional.of(employee2);
        given(repository.findById(employee2.getId())).willReturn(optEmployee2);
        String jsonOutput2 = mapper.writeValueAsString(employee2);

        mockMvc.perform(get("/api/employees/"+employee2.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonOutput2));

        Optional<Employee> optEmployee3 = Optional.of(employee3);
        given(repository.findById(employee3.getId())).willReturn(optEmployee3);
        String jsonOutput3 = mapper.writeValueAsString(employee3);

        mockMvc.perform(get("/api/employees/"+employee3.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonOutput3));

        Optional<Employee> optEmployee4 = Optional.of(employee4);
        given(repository.findById(employee4.getId())).willReturn(optEmployee4);
        String jsonOutput4 = mapper.writeValueAsString(employee4);

        mockMvc.perform(get("/api/employees/"+employee4.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonOutput4));

        Optional<Employee> optEmployee5 = Optional.of(employee5);
        given(repository.findById(employee5.getId())).willReturn(optEmployee5);
        String jsonOutput5 = mapper.writeValueAsString(employee5);

        mockMvc.perform(get("/api/employees/"+employee5.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonOutput5));

    }

    @Test
    public void shouldReturnCorrectEmployeeWhenSearchedByEmail() throws Exception {
        repository.deleteAll();

        Optional<Employee> optEmployee1 = Optional.of(employee1);
        given(repository.findByEmail(employee1.getEmail())).willReturn(optEmployee1);
        String jsonOutput1 = mapper.writeValueAsString(employee1);

        mockMvc.perform(get("/api/employees/findByEmail/"+employee1.getEmail()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonOutput1));

        Optional<Employee> optEmployee2 = Optional.of(employee2);
        given(repository.findByEmail(employee2.getEmail())).willReturn(optEmployee2);
        String jsonOutput2 = mapper.writeValueAsString(employee2);

        mockMvc.perform(get("/api/employees/findByEmail/"+employee2.getEmail()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonOutput2));

        Optional<Employee> optEmployee3 = Optional.of(employee3);
        given(repository.findByEmail(employee3.getEmail())).willReturn(optEmployee3);
        String jsonOutput3 = mapper.writeValueAsString(employee3);

        mockMvc.perform(get("/api/employees/findByEmail/"+employee3.getEmail()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonOutput3));

        Optional<Employee> optEmployee4 = Optional.of(employee4);
        given(repository.findByEmail(employee4.getEmail())).willReturn(optEmployee4);
        String jsonOutput4 = mapper.writeValueAsString(employee4);

        mockMvc.perform(get("/api/employees/findByEmail/"+employee4.getEmail()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonOutput4));

        Optional<Employee> optEmployee5 = Optional.of(employee5);
        given(repository.findByEmail(employee5.getEmail())).willReturn(optEmployee5);
        String jsonOutput5 = mapper.writeValueAsString(employee5);

        mockMvc.perform(get("/api/employees/findByEmail/"+employee5.getEmail()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonOutput5));
    }

    @Test
    public void shouldReturnCorrectEmployeeWhenSearchedByName() throws Exception{
        repository.deleteAll();

        Optional<Employee> optEmployee1 = Optional.of(employee1);
        given(repository.findByName(employee1.getName())).willReturn(optEmployee1);
        String jsonOutput1 = mapper.writeValueAsString(employee1);

        mockMvc.perform(get("/api/employees/findByName/"+employee1.getName()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonOutput1));

        Optional<Employee> optEmployee2 = Optional.of(employee2);
        given(repository.findByName(employee2.getName())).willReturn(optEmployee2);
        String jsonOutput2 = mapper.writeValueAsString(employee2);

        mockMvc.perform(get("/api/employees/findByName/"+employee2.getName()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonOutput2));

        Optional<Employee> optEmployee3 = Optional.of(employee3);
        given(repository.findByName(employee3.getName())).willReturn(optEmployee3);
        String jsonOutput3 = mapper.writeValueAsString(employee3);

        mockMvc.perform(get("/api/employees/findByName/"+employee3.getName()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonOutput3));

        Optional<Employee> optEmployee4 = Optional.of(employee4);
        given(repository.findByName(employee4.getName())).willReturn(optEmployee4);
        String jsonOutput4 = mapper.writeValueAsString(employee4);

        mockMvc.perform(get("/api/employees/findByName/"+employee4.getName()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonOutput4));

        Optional<Employee> optEmployee5 = Optional.of(employee5);
        given(repository.findByName(employee5.getName())).willReturn(optEmployee5);
        String jsonOutput5 = mapper.writeValueAsString(employee5);

        mockMvc.perform(get("/api/employees/findByName/"+employee5.getName()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonOutput5));

    }

    @Test
    public void shouldReturnCorrectEmployeesWhenSearchedByTitle() throws Exception{
        repository.deleteAll();

        // Test All Employees with Worker title
        List<Employee> workerEmployeeList = new ArrayList<>();
        workerEmployeeList.add(employee2);
        workerEmployeeList.add(employee3);
        workerEmployeeList.add(employee5);

        given(repository.findByTitle("Worker")).willReturn(workerEmployeeList);
        String jsonOutputWorker = mapper.writeValueAsString(workerEmployeeList);

        mockMvc.perform(get("/api/employees/findByTitle/Worker"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonOutputWorker));
        // Test end

        // Test All Employees with Architect title
        List<Employee> architectEmployeeList = new ArrayList<>();
        workerEmployeeList.add(employee1);
        workerEmployeeList.add(employee4);

        given(repository.findByTitle("Architect")).willReturn(architectEmployeeList);
        String jsonOutputArchitect = mapper.writeValueAsString(architectEmployeeList);

        mockMvc.perform(get("/api/employees/findByTitle/Architect"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonOutputArchitect));
        // Test end
    }

    @Test
    public void shouldReturnCorrectEmployeesWhenSearchedByProjectId() throws Exception{
        repository.deleteAll();

        // Test All Employees working on Project 1
        List<Employee> project1EmployeeList = new ArrayList<>();
        project1EmployeeList.add(employee1);
        project1EmployeeList.add(employee2);
        project1EmployeeList.add(employee3);

        given(repository.findByProjectId(1)).willReturn(project1EmployeeList);
        String jsonOutputProject1 = mapper.writeValueAsString(project1EmployeeList);

        mockMvc.perform(get("/api/employees/findByProjectId/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonOutputProject1));
        // Test end

        // Test All Employees working on Project 2
        List<Employee> project2EmployeeList = new ArrayList<>();
        project2EmployeeList.add(employee4);
        project2EmployeeList.add(employee5);

        given(repository.findByProjectId(2)).willReturn(project2EmployeeList);
        String jsonOutputProject2 = mapper.writeValueAsString(project2EmployeeList);

        mockMvc.perform(get("/api/employees/findByProjectId/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonOutputProject2));
        // Test end
    }

    @Test
    public void shouldReturnNewEmployeeOnPostRequest() throws Exception {
        repository.deleteAll();

        String inputEmployeeJson = mapper.writeValueAsString(inputEmployee);

        Employee outputEmployee = new Employee();
        outputEmployee.setId(1);
        outputEmployee.setProjectId(5);
        outputEmployee.setPassword("defaultPass");
        outputEmployee.setTitle("Architect");
        outputEmployee.setName("Amal Janabayev");
        outputEmployee.setSalary(new BigDecimal("40.000"));
        outputEmployee.setYearsOfExperience(3);
        outputEmployee.setEmail("amalj2426@gmail.com");
        outputEmployee.setPhoneNumber("3143784296");
        outputEmployee.setUsername("amalj24");

        String outputEmployeeJson = mapper.writeValueAsString(outputEmployee);

        given(repository.save(inputEmployee)).willReturn(outputEmployee);

        mockMvc.perform(post("/api/employees")
                .content(inputEmployeeJson)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputEmployeeJson));
    }
}