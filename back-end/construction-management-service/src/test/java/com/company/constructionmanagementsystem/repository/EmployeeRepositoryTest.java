package com.company.constructionmanagementsystem.repository;

import com.company.constructionmanagementsystem.model.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRepositoryTest {
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

    //
    @Test
    public void addFindDeleteEmployee() {
        employeeRepository.deleteAll();

        LocalDate birth = LocalDate.of(1999,9 ,9);
        LocalDate since = LocalDate.now();
        MathContext mathContext = new MathContext(4);

        Employee employee = new Employee();
        employee.setTitle("Worker");
        employee.setName("John Doe");
        employee.setDateOfBirth(birth);
        employee.setSalary(new BigDecimal(123.23).round(mathContext));
        employee.setYearsOfExperience(5);
        employee.setEmail("abc@email.com");
        employee.setPhoneNumber("1234567890");
        employee.setUsername("johnusername");
        employee.setPassword("123456");
        employee.setUserSince(since);
        // add
        employee = employeeRepository.save(employee);

        assertEquals(1, employeeRepository.findAll().size());

        // find
        Employee employee1 = employeeRepository.findById(employee.getId()).get();

        employee1.setSalary(employee1.getSalary().round(mathContext));

        assertEquals(employee1, employee);

        // delete
        employeeRepository.deleteById(employee.getId());

        assertEquals(0, employeeRepository.findAll().size());
    }

    @Test
    public void saveAndFlushEmployee() {
        employeeRepository.deleteAll();

        LocalDate birth = LocalDate.of(1990,1,11);
        LocalDate since = LocalDate.now();
        MathContext mathContext = new MathContext(4);



        Employee employee = new Employee();
        employee.setTitle("Worker");
        employee.setName("Dan");
        employee.setDateOfBirth(birth);
        employee.setSalary(new BigDecimal(123.23).round(mathContext));
        employee.setYearsOfExperience(5);
        employee.setEmail("abc@email.com");
        employee.setPhoneNumber("1234567890");
        employee.setUsername("danusername");
        employee.setPassword("123456");
        employee.setUserSince(since);
        employee = employeeRepository.save(employee);

        employee.setTitle("Manager");
        employee.setSalary(new BigDecimal(321.321).round(mathContext));

        employeeRepository.saveAndFlush(employee);

        Employee employee1 = employeeRepository.findById(employee.getId()).get();
        employee1.setSalary(employee1.getSalary().round(mathContext));


        assertEquals(employee1, employee);

    }

    @Test
    public void findAllEmployees() {
        employeeRepository.deleteAll();

        LocalDate birth1 = LocalDate.of(1999,9 ,9);
        LocalDate since1 = LocalDate.now();
        MathContext mathContext = new MathContext(4);

        Employee employee1 = new Employee();
        employee1.setTitle("Worker");
        employee1.setName("John Doe");
        employee1.setDateOfBirth(birth1);
        employee1.setSalary(new BigDecimal(123.23).round(mathContext));
        employee1.setYearsOfExperience(5);
        employee1.setEmail("abcd@email.com");
        employee1.setPhoneNumber("1234567890");
        employee1.setUsername("johnusername");
        employee1.setPassword("123456");
        employee1.setUserSince(since1);
        employee1 = employeeRepository.save(employee1);

        LocalDate birth2 = LocalDate.of(1990,1 ,1);
        LocalDate since2 = LocalDate.now();

        Employee employee2 = new Employee();
        employee2.setTitle("Manager");
        employee2.setName("Joe Hee");
        employee2.setDateOfBirth(birth2);
        employee2.setSalary(new BigDecimal(123.23).round(mathContext));
        employee2.setYearsOfExperience(5);
        employee2.setEmail("abc@email.com");
        employee2.setPhoneNumber("0987654321");
        employee2.setUsername("joeusername");
        employee2.setPassword("654321");
        employee2.setUserSince(since2);
        employeeRepository.save(employee2);

        List<Employee> employeeList = employeeRepository.findAll();

        assertEquals(2, employeeList.size());

    }

    @Test
    public void findEmployeesByTitle() {
        employeeRepository.deleteAll();

        LocalDate birth = LocalDate.of(1999,9 ,9);
        LocalDate since = LocalDate.now();
        MathContext mathContext = new MathContext(4);

        Employee employee = new Employee();
        employee.setTitle("Worker");
        employee.setName("John Doe");
        employee.setDateOfBirth(birth);
        employee.setSalary(new BigDecimal(123.23).round(mathContext));
        employee.setYearsOfExperience(5);
        employee.setEmail("abc@email.com");
        employee.setPhoneNumber("1234567890");
        employee.setUsername("johnusername");
        employee.setPassword("123456");
        employee.setUserSince(since);
        employee = employeeRepository.save(employee);


        // findByTitle
        List<Employee> byTitle = employeeRepository.findByTitle("Worker");

        assertEquals(byTitle.size(), 1);

        Employee employee1 = byTitle.get(0);
        employee1.setSalary(employee1.getSalary().round(mathContext));

        assertEquals(employee1, employee);
    }

    @Test
    public void findEmployeesByProjectId() {
        employeeRepository.deleteAll();

        LocalDate birth = LocalDate.of(1999,9 ,9);
        LocalDate since = LocalDate.now();
        MathContext mathContext = new MathContext(4);

        Employee employee = new Employee();
        employee.setTitle("Worker");
        employee.setName("John Doe");
        employee.setDateOfBirth(birth);
        employee.setProjectId(1);
        employee.setSalary(new BigDecimal(123.23).round(mathContext));
        employee.setYearsOfExperience(5);
        employee.setEmail("abc@email.com");
        employee.setPhoneNumber("1234567890");
        employee.setUsername("johnusername");
        employee.setPassword("123456");
        employee.setUserSince(since);
        employee = employeeRepository.save(employee);

        // findByProjectId
        List<Employee> byProjectId = employeeRepository.findByProjectId(1);

        assertEquals(1, byProjectId.size());

        Employee employee1 = byProjectId.get(0);
        employee1.setSalary(employee1.getSalary().round(mathContext));

        assertEquals(employee1, employee);
    }

    @Test
    public void findEmployByEmail() {
        employeeRepository.deleteAll();

        LocalDate birth = LocalDate.of(1999,9 ,9);
        LocalDate since = LocalDate.now();
        MathContext mathContext = new MathContext(4);

        Employee employee = new Employee();
        employee.setTitle("Worker");
        employee.setName("John Doe");
        employee.setDateOfBirth(birth);
        employee.setSalary(new BigDecimal(123.23).round(mathContext));
        employee.setYearsOfExperience(5);
        employee.setEmail("abc@email.com");
        employee.setPhoneNumber("1234567890");
        employee.setUsername("johnusername");
        employee.setPassword("123456");
        employee.setUserSince(since);
        employee = employeeRepository.save(employee);

        // findByEmail
        Employee byEmail = employeeRepository.findByEmail("abc@email.com").get();
        byEmail.setSalary(byEmail.getSalary().round(mathContext));

        assertEquals(byEmail, employee);
    }

    @Test
    public void findEmployeeByName() {
        employeeRepository.deleteAll();

        LocalDate birth = LocalDate.of(1999,9 ,9);
        LocalDate since = LocalDate.now();
        MathContext mathContext = new MathContext(4);

        Employee employee = new Employee();
        employee.setTitle("Worker");
        employee.setName("John Doe");
        employee.setDateOfBirth(birth);
        employee.setSalary(new BigDecimal(123.23).round(mathContext));
        employee.setYearsOfExperience(5);
        employee.setEmail("abc@email.com");
        employee.setPhoneNumber("1234567890");
        employee.setUsername("johnusername");
        employee.setPassword("123456");
        employee.setUserSince(since);
        employee = employeeRepository.save(employee);

        // findByName
        Employee byName = employeeRepository.findByName("John Doe").get(0);
        byName.setSalary(byName.getSalary().round(mathContext));

        assertEquals(byName, employee);

    }

    @Test
    public void findByUsername() {
        employeeRepository.deleteAll();

        LocalDate birth = LocalDate.of(1999,9 ,9);
        LocalDate since = LocalDate.now();
        MathContext mathContext = new MathContext(4);

        Employee employee = new Employee();
        employee.setTitle("Worker");
        employee.setName("John Doe");
        employee.setDateOfBirth(birth);
        employee.setSalary(new BigDecimal(123.23).round(mathContext));
        employee.setYearsOfExperience(5);
        employee.setEmail("abc@email.com");
        employee.setPhoneNumber("1234567890");
        employee.setUsername("johnusername");
        employee.setPassword("123456");
        employee.setUserSince(since);
        employee = employeeRepository.save(employee);

        // findByName
        Employee byUserName = employeeRepository.findByUsername("johnusername").get();
        byUserName.setSalary(byUserName.getSalary().round(mathContext));

        assertEquals(byUserName, employee);

    }

}