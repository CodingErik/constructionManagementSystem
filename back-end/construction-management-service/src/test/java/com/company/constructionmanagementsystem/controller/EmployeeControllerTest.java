package com.company.constructionmanagementsystem.controller;

import com.company.constructionmanagementsystem.model.Employee;
import com.company.constructionmanagementsystem.model.Project;
import com.company.constructionmanagementsystem.model.Task;
import com.company.constructionmanagementsystem.repository.EmployeeRepository;
import com.company.constructionmanagementsystem.repository.ProjectRepository;
import com.company.constructionmanagementsystem.repository.TaskRepository;
import com.company.constructionmanagementsystem.security.JwtConverter;
import com.company.constructionmanagementsystem.service.EmployeeServiceLayer;
import com.company.constructionmanagementsystem.util.LoginDetailsService;
import com.company.constructionmanagementsystem.viewmodel.EmployeeViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class EmployeeControllerTest {

    @MockBean
    EmployeeRepository employeeRepository;

    @MockBean
    ProjectRepository projectRepository;

    @MockBean
    TaskRepository taskRepository;

    @MockBean
    EmployeeServiceLayer employeeServiceLayer;

    @MockBean
    JwtConverter jwtConverter;

    @MockBean
    LoginDetailsService loginDetailsService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    Employee employee1;
    Employee employee2;
    Employee employee3;
    Employee employee4;
    Employee employee5;
    List<Employee> employeeInputList;

    EmployeeViewModel employeeViewModel1;
    EmployeeViewModel employeeViewModel2;
    EmployeeViewModel employeeViewModel3;
    EmployeeViewModel employeeViewModel4;
    EmployeeViewModel employeeViewModel5;
    List<EmployeeViewModel> employeeViewModelList;

    Project project1;
    Project project2;
    List<Project> projectList;

    Task task1;
    Task task2;
    Task task3;
    Task task4;
    Task task5;
    List<Task> taskList;

    @Before
    public void setUp() throws Exception {
        java.time.LocalDate birth = java.time.LocalDate.of(1999,9 ,9);
        java.time.LocalDate since = java.time.LocalDate.now();
        java.time.LocalDate deadline = java.time.LocalDate.of(2010,4 ,4);
        java.time.LocalDate startDate = java.time.LocalDate.now();
        MathContext mathContext = new MathContext(4);

        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        employee1 = new Employee(1,1,"Architect","Amal",birth,new BigDecimal(430.33).round(mathContext),4,"amalj2426@gmail.com","314-378-4297","amalj",null,since);
        employee2 = new Employee(2,1,"Worker","Hannah",birth,new BigDecimal(430.33).round(mathContext),2,"hannahb@gmail.com","422-987-2243","hannahb",null,since);
        employee3 = new Employee(3,2,"Architect","Nargiza",birth,new BigDecimal(430.33).round(mathContext),8,"nargiza@gmail.com","224-493-4288","narg",null,since);
        employee4 = new Employee(4,2,"Worker","Milana",birth,new BigDecimal(430.33).round(mathContext),1,"milana@gmail.com","734-902-2987","milan",null,since);
        employee5 = new Employee(5,2,"Worker","Tamila",birth,new BigDecimal(430.33).round(mathContext),1,"tamila@gmail.com","417-487-4227","tamil",null,since);
        employeeInputList = new ArrayList<>();
        employeeInputList.add(employee1);
        employeeInputList.add(employee2);
        employeeInputList.add(employee3);
        employeeInputList.add(employee4);
        employeeInputList.add(employee5);

        project1 = new Project(1,"Project1",deadline,startDate,"Kitchen",true,true,new BigDecimal(9800.34).round(mathContext),new BigDecimal(3480.16).round(mathContext),new BigDecimal(13280.50).round(mathContext),"in_progress");
        project2 = new Project(2,"Project2",deadline,startDate,"Living Room",false,true,new BigDecimal(9000.50).round(mathContext),new BigDecimal(9000.50).round(mathContext),new BigDecimal(18001.00).round(mathContext),"completed");
        projectList = new ArrayList<>();
        projectList.add(project1);
        projectList.add(project2);

        task1 = new Task(1,1,3,"Install Windows",startDate,deadline,"Install windows","in_progress");
        task2 = new Task(2,1,2,"Remove old panel floors",startDate,deadline,"Remove floors","completed");
        task3 = new Task(3,2,1,"Paint walls",startDate,deadline,"Paint walls with grey color","in_progress");
        task4 = new Task(4,2,4,"Dispose of garbage",startDate,deadline,"Dispose of construction garbage","in_progress");
        task5 = new Task(5,2,5,"Install shelves",startDate,deadline,"Install shelves in the main part of the room","completed");
        taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        taskList.add(task4);
        taskList.add(task5);

        employeeViewModel1 = buildEmployeeViewModel(employee1);
        employeeViewModel2 = buildEmployeeViewModel(employee2);
        employeeViewModel3 = buildEmployeeViewModel(employee3);
        employeeViewModel4 = buildEmployeeViewModel(employee4);
        employeeViewModel5 = buildEmployeeViewModel(employee5);

        employeeViewModelList = new ArrayList<>();
        employeeViewModelList.add(employeeViewModel1);
        employeeViewModelList.add(employeeViewModel2);
        employeeViewModelList.add(employeeViewModel3);
        employeeViewModelList.add(employeeViewModel4);
        employeeViewModelList.add(employeeViewModel5);

    }

    @Test
    @WithMockUser(roles = {"admin"})
    public void shouldReturnAllEmployees() throws Exception{
        employeeRepository.deleteAll();

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        employeeRepository.save(employee4);
        employeeRepository.save(employee5);

        given(employeeServiceLayer.findAllEmployees()).willReturn(employeeViewModelList);

        String jsonOutput = mapper.writeValueAsString(employeeViewModelList);

        mockMvc.perform(get("/api/employees"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonOutput));
    }

    @Test
    public void shouldReturnEmployeeWithCorrectId() throws Exception {
        employeeRepository.deleteAll();
        projectRepository.deleteAll();
        taskRepository.deleteAll();

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        employeeRepository.save(employee4);
        employeeRepository.save(employee5);

        projectRepository.save(project1);
        projectRepository.save(project2);

        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);
        taskRepository.save(task4);
        taskRepository.save(task5);

        EmployeeViewModel inputEmployee = buildEmployeeViewModel(employee1);
        String outputJson = mapper.writeValueAsString(inputEmployee);

        given(employeeServiceLayer.findEmployeeById(inputEmployee.getId())).willReturn(inputEmployee);

        mockMvc.perform(get("/api/employees/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }


    @Test
    public void shouldReturnEmployeeWithCorrectEmail() throws Exception {
        employeeRepository.deleteAll();
        projectRepository.deleteAll();
        taskRepository.deleteAll();

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        employeeRepository.save(employee4);
        employeeRepository.save(employee5);

        projectRepository.save(project1);
        projectRepository.save(project2);

        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);
        taskRepository.save(task4);
        taskRepository.save(task5);

        EmployeeViewModel inputEmployee = buildEmployeeViewModel(employee2);
        String outputJson = mapper.writeValueAsString(inputEmployee);

        given(employeeServiceLayer.findEmployeeByEmail(inputEmployee.getEmail())).willReturn(inputEmployee);

        mockMvc.perform(get("/api/employees/findByEmail/"+inputEmployee.getEmail()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldReturnEmployeeWithCorrectName() throws Exception{
        employeeRepository.deleteAll();
        projectRepository.deleteAll();
        taskRepository.deleteAll();

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        employeeRepository.save(employee4);
        employeeRepository.save(employee5);

        projectRepository.save(project1);
        projectRepository.save(project2);

        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);
        taskRepository.save(task4);
        taskRepository.save(task5);

        EmployeeViewModel inputEmployee = buildEmployeeViewModel(employee3);

        List<EmployeeViewModel> inputEmployeeViewModelList = new ArrayList<>();

        for(Employee employee : employeeInputList){
            if(employee.getName() == inputEmployee.getName()){
                EmployeeViewModel evm = buildEmployeeViewModel(employee);
                inputEmployeeViewModelList.add(evm);
            }
        }

        String outputJson = mapper.writeValueAsString(inputEmployeeViewModelList);

        given(employeeServiceLayer.findEmployeeByName(inputEmployee.getName())).willReturn(inputEmployeeViewModelList);

        mockMvc.perform(get("/api/employees/findByName/"+inputEmployee.getName()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnEmployeeWithCorrectUsername() throws Exception {
        employeeRepository.deleteAll();
        projectRepository.deleteAll();
        taskRepository.deleteAll();

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        employeeRepository.save(employee4);
        employeeRepository.save(employee5);

        projectRepository.save(project1);
        projectRepository.save(project2);

        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);
        taskRepository.save(task4);
        taskRepository.save(task5);

        EmployeeViewModel inputEmployee = buildEmployeeViewModel(employee4);
        String outputJson = mapper.writeValueAsString(inputEmployee);

        given(employeeServiceLayer.findEmployeeByUsername(inputEmployee.getUsername())).willReturn(inputEmployee);

        mockMvc.perform(get("/api/employees/findByUsername/"+inputEmployee.getUsername()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnListOfEmployeesWithCorrectTitle() throws Exception {
        employeeRepository.deleteAll();
        projectRepository.deleteAll();
        taskRepository.deleteAll();

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        employeeRepository.save(employee4);
        employeeRepository.save(employee5);

        projectRepository.save(project1);
        projectRepository.save(project2);

        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);
        taskRepository.save(task4);
        taskRepository.save(task5);

        List<EmployeeViewModel> inputEmployeeViewModelList = new ArrayList<>();

        for(Employee employee : employeeInputList){
            if(employee.getTitle() == "Architect"){
                EmployeeViewModel evm = buildEmployeeViewModel(employee);
                inputEmployeeViewModelList.add(evm);
            }
        }

        String outputJson = mapper.writeValueAsString(inputEmployeeViewModelList);

        given(employeeServiceLayer.findEmployeesByTitle("Architect")).willReturn(inputEmployeeViewModelList);

        mockMvc.perform(get("/api/employees/findByTitle/Architect"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldReturnListOfEmployeesWithCorrectProjectId() throws Exception {
        employeeRepository.deleteAll();
        projectRepository.deleteAll();
        taskRepository.deleteAll();

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        employeeRepository.save(employee4);
        employeeRepository.save(employee5);

        projectRepository.save(project1);
        projectRepository.save(project2);

        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);
        taskRepository.save(task4);
        taskRepository.save(task5);

        List<EmployeeViewModel> inputEmployeeViewModelList = new ArrayList<>();

        for(Employee employee : employeeInputList){
            if(employee.getProjectId() == 1){
                EmployeeViewModel evm = buildEmployeeViewModel(employee);
                inputEmployeeViewModelList.add(evm);
            }
        }

        String outputJson = mapper.writeValueAsString(inputEmployeeViewModelList);

        given(employeeServiceLayer.findEmployeesByProjectId(1)).willReturn(inputEmployeeViewModelList);

        mockMvc.perform(get("/api/employees/findByProjectId/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }


    @Test
    public void shouldReturnCorrectEmployeeWhenRegistering() throws Exception {

        java.time.LocalDate birth = java.time.LocalDate.of(1999,9 ,9);
        java.time.LocalDate since = java.time.LocalDate.now();
        MathContext mathContext = new MathContext(4);

        Employee inputEmployee = new Employee();
        inputEmployee.setId(1);
        inputEmployee.setTitle("architect");
        inputEmployee.setName("amal");
        inputEmployee.setEmail("amalj2426@gmail.com");
        inputEmployee.setYearsOfExperience(0);
        inputEmployee.setUsername("amalj");
        inputEmployee.setPassword(null);
        inputEmployee.setUserSince(since);

        String inputJson = mapper.writeValueAsString(inputEmployee);
        // ******************************************
        // ******************************************
        Employee outputEmployee = new Employee();
        outputEmployee.setId(1);
        outputEmployee.setProjectId(0);
        outputEmployee.setTitle("architect");
        outputEmployee.setName("amal");
        outputEmployee.setEmail("amalj2426@gmail.com");
        outputEmployee.setUsername("amalj");
        outputEmployee.setYearsOfExperience(0);
        outputEmployee.setPassword("defaultPass");
        outputEmployee.setUserSince(since);

        String outputJson = mapper.writeValueAsString(outputEmployee);

        given(employeeRepository.save(outputEmployee)).willReturn(outputEmployee);

        mockMvc.perform(
                post("/api/employees/register")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));

    }

    @Test
    @WithMockUser(roles = {"admin"})
    public void shouldUpdateEmployee() throws Exception {
        java.time.LocalDate birth = java.time.LocalDate.of(1999,9 ,9);
        java.time.LocalDate since = java.time.LocalDate.now();
        MathContext mathContext = new MathContext(4);

        Employee inputEmployee = new Employee(1,1,"Architect","Amal",birth,new BigDecimal(430.33).round(mathContext),4,"amalj2426@gmail.com","314-378-4297","amalj","password",since);

        String inputJson = mapper.writeValueAsString(inputEmployee);

        mockMvc.perform(
                put("/api/employees")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteEmployeeAndAllTasksWithIt() throws Exception {

        mockMvc.perform(delete("/api/employees/1")).andDo(print()).andExpect(status().isNoContent());
    }
  
  
    @WithMockUser(roles = {"Admin"})
    public void shouldUpdatePassword() throws Exception {
        Map<String, String> inputBody = new HashMap<>();
        inputBody.put("id", "1");
        inputBody.put("password", "newPass");

        String inputJson = mapper.writeValueAsString(inputBody);

        mockMvc.perform(
                put("/api/resetPassword")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    private EmployeeViewModel buildEmployeeViewModel(Employee employee){

        List<Task> taskList = taskRepository.findAllTasksByEmployeeId(employee.getId());
        EmployeeViewModel evm = new EmployeeViewModel();

        if (!projectRepository.findById(employee.getProjectId()).isPresent()) {
            evm.setProject(null);
        } else {
            evm.setProject(projectRepository.findById(employee.getProjectId()).get());
        }

        evm.setTaskList(taskList);
        evm.setId(employee.getId());
        evm.setEmail(employee.getEmail());
        evm.setName(employee.getName());
        evm.setSalary(employee.getSalary());
        evm.setTitle(employee.getTitle());
        evm.setDateOfBirth(employee.getDateOfBirth());
        evm.setPassword(employee.getPassword());
        evm.setPhoneNumber(employee.getPhoneNumber());
        evm.setUsername(employee.getUsername());
        evm.setUserSince(employee.getUserSince());
        evm.setYearsOfExperience(employee.getYearsOfExperience());

        return evm;
    }



}