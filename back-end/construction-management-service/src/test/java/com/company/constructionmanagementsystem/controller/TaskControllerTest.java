package com.company.constructionmanagementsystem.controller;

import com.company.constructionmanagementsystem.model.Employee;
import com.company.constructionmanagementsystem.model.Project;
import com.company.constructionmanagementsystem.model.Task;
import com.company.constructionmanagementsystem.repository.EmployeeRepository;
import com.company.constructionmanagementsystem.repository.ProjectRepository;
import com.company.constructionmanagementsystem.repository.TaskRepository;
import com.company.constructionmanagementsystem.security.JwtConverter;
import com.company.constructionmanagementsystem.service.EmployeeServiceLayer;
import com.company.constructionmanagementsystem.service.TaskServiceLayer;
import com.company.constructionmanagementsystem.util.LoginDetailsService;
import com.company.constructionmanagementsystem.util.feign.MachineWarehouseClient;
import com.company.constructionmanagementsystem.util.feign.MaterialWarehouseClient;
import com.company.constructionmanagementsystem.viewmodel.EmployeeViewModel;
import com.company.constructionmanagementsystem.viewmodel.TaskViewModel;
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
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.cloud.openfeign.FeignLoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class TaskControllerTest {

    @MockBean
    TaskRepository taskRepository;

    @MockBean
    EmployeeRepository employeeRepository;

    @MockBean
    ProjectRepository projectRepository;

    @MockBean
    TaskServiceLayer taskServiceLayer;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    JwtConverter jwtConverter;

    @MockBean
    LoginDetailsService loginDetailsService;

    @MockBean
    FeignContext feignContext;

    @MockBean
    FeignLoggerFactory feignLoggerFactory;

    @MockBean
    MachineWarehouseClient machineWarehouseClient;

    @MockBean
    MaterialWarehouseClient materialWarehouseClient;

    @MockBean
    EmployeeServiceLayer employeeServiceLayer;

    private ObjectMapper mapper = new ObjectMapper();


    private Task newTask;
    private TaskViewModel taskViewModel;
    private Task completedTask;
    Task updatedCompletedTask;
    String jsonNewTask;
    String jsonCompletedTask;
    String jsonUpdatedCompletedTask;
    private Task anotherTask;
    private List<Task> taskList = new ArrayList<>();
    private List<TaskViewModel> taskViewModelList = new ArrayList<>();
    private Project project;
    private Employee employee;

    @Before
    public void setUp() throws Exception {

        taskRepository.deleteAll();

        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        java.time.LocalDate startDate = java.time.LocalDate.of(1999, 9, 9);
        java.time.LocalDate deadLine = java.time.LocalDate.of(1999, 9, 9);

        /** incoming from front end */
        newTask = new Task("take out the trash", 1, null, startDate, deadLine, "need to take out trash", "in_progress");

        jsonNewTask = mapper.writeValueAsString(newTask);

        /** database task created and completed */
        completedTask = new Task(1, 1, 2, "take out the trash", startDate, deadLine, "need to take out trash", "completed");
        updatedCompletedTask = new Task(1, 1, 2, "take out the trash1", startDate, deadLine, "need to take out trash", "completed");

        jsonCompletedTask = mapper.writeValueAsString(completedTask);
        jsonUpdatedCompletedTask = mapper.writeValueAsString(updatedCompletedTask);

        /** another task to include in the database */
        anotherTask = new Task(2, 2, 4, "walk the dog", startDate, deadLine, "need to walk the fat dog", "completed");

        /** list of tasks in the database */
        taskList.add(completedTask);
        taskRepository.save(completedTask);

        project = new Project(1, "projectDog", deadLine, startDate, "backyard", false, false, new BigDecimal("10.00"), new BigDecimal("10.00"), new BigDecimal("10.00"), "completed");
        employee = new Employee(2, 1, "admin", "bob", startDate, new BigDecimal("100000"), 100, "email@email", "123-123-1234", "username", "paswword", startDate);
        taskViewModel = new TaskViewModel(1, project, employee, "take out the trash", startDate, deadLine, "need to take out trash", "completed");

        taskViewModelList.add(taskViewModel);

    }


    @Test
    @WithMockUser(roles = {"admin"})
    public void addTask() throws Exception {

        given(taskRepository.save(newTask)).willReturn(completedTask);

        mockMvc.perform(
                        post("/api/tasks")
                                .content(jsonNewTask)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(jsonCompletedTask));
    }

    @Test
    @WithMockUser(roles = {"admin"})
    public void getTaskById() throws Exception {

        given(taskServiceLayer.findTaskById(completedTask.getId())).willReturn(taskViewModel);
        String jsonTaskVM = mapper.writeValueAsString(taskViewModel);

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("taskId", "1");


        mockMvc.perform(get("/api/tasks/id")
                        .param("taskId", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonTaskVM));

    }

    @Test
    @WithMockUser(roles = {"admin"})
    public void getAllTasks() throws Exception {


        given(taskServiceLayer.findAllTasks()).willReturn(taskViewModelList);
        given(taskRepository.findAll()).willReturn(taskList);

        String jsonMachine = mapper.writeValueAsString(taskViewModelList);

        mockMvc.perform(get("/api/tasks"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonMachine));


    }

    @Test
    @WithMockUser(roles = {"admin"})
    public void updateTask() throws Exception {
//   /api/tasks")

        given(taskRepository.save(updatedCompletedTask)).willReturn(updatedCompletedTask);


        mockMvc.perform(
                        put("/api/tasks")
                                .content(jsonUpdatedCompletedTask)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());

    }

    @Test
    public void shouldReturn422IfNoName() {
        taskRepository.deleteAll();
        LocalDate startDate = LocalDate.now();
        LocalDate deadline = LocalDate.now();

        Task task = new Task();
        task.setProjectId(1);
        task.setEmployeeId(1);
        task.setStartDate(startDate);
        task.setDeadline(deadline);
        task.setDescription("This is a task.");
        task.setStatus("In progress");

        taskRepository.save(task);
    }

}
