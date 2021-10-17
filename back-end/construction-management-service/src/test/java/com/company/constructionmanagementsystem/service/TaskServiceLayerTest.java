package com.company.constructionmanagementsystem.service;

import com.company.constructionmanagementsystem.model.Task;
import com.company.constructionmanagementsystem.repository.EmployeeRepository;
import com.company.constructionmanagementsystem.repository.ProjectRepository;
import com.company.constructionmanagementsystem.repository.TaskRepository;
import com.company.constructionmanagementsystem.security.JwtConverter;
import com.company.constructionmanagementsystem.util.LoginDetailsService;
import com.company.constructionmanagementsystem.util.feign.MachineWarehouseClient;
import com.company.constructionmanagementsystem.util.feign.MaterialWarehouseClient;
import com.company.constructionmanagementsystem.viewmodel.TaskViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.cloud.openfeign.FeignLoggerFactory;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TaskServiceLayerTest {


    @Mock
    ProjectRepository projectRepository;
    @Mock
    EmployeeRepository employeeRepository;
    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    TaskServiceLayer service;

    @MockBean
    FeignContext feignContext;

    @MockBean
    FeignLoggerFactory feignLoggerFactory;

    @MockBean
    MachineWarehouseClient machineWarehouseClient;

    @MockBean
    MaterialWarehouseClient materialWarehouseClient;

    @MockBean
    JwtConverter jwtConverter;

    @MockBean
    LoginDetailsService loginDetailsService;

    TaskViewModel taskViewModel;

    ObjectMapper mapper = new ObjectMapper();

    Task task;
    Task otherTask;
    List<Task>  taskList = new ArrayList<>();
    java.time.LocalDate startDate;
    java.time.LocalDate deadLine;
    List<Task> mainLists = new ArrayList<>();
    List<Task> otherLists = new ArrayList<>();

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        startDate = java.time.LocalDate.of(1999, 9, 9);
        deadLine = java.time.LocalDate.of(2010, 4, 4);

        task = new Task(1,1,1,"walk",startDate, deadLine, "walk around","in_progress");
        otherTask = new Task(2,1,2,"running",startDate, deadLine, "run  around","in_progress");

        taskList.add(task);
        taskList.add(otherTask);

        mainLists.add(task);

        otherLists.add(otherTask);

    }

    @Test
    public void findAllTasks() {

        when(taskRepository.findAll()).thenReturn(taskList);

        List<TaskViewModel> taskViewModelList = service.findAllTasks();

        assertEquals(2, taskViewModelList.size());


    }

    @Test
    public void findTaskById() {

        when(taskRepository.getById(otherTask.getId())).thenReturn(otherTask);

        TaskViewModel taskById = service.findTaskById(otherTask.getId());

        assertEquals(2, taskById.getId().intValue());
        assertEquals("running", taskById.getName().toString());

    }

    @Test
    public void findTasksByName() {


        when(taskRepository.findAllTasksByName("running")).thenReturn(otherLists);

        List<TaskViewModel> taskViewModelList =  service.findTasksByName("running");

        assertEquals( 1, taskViewModelList.size());

    }

    @Test
    public void findAllTasksByProjectId() {

        when(taskRepository.findAllTasksByProjectId(1)).thenReturn(taskList);

        List<TaskViewModel> taskViewModelList = service.findAllTasksByProjectId(1);

        assertEquals(2, taskViewModelList.size());

    }

    @Test
    public void findAllTasksByEmployeeId() {


        when(taskRepository.findAllTasksByEmployeeId(2)).thenReturn(otherLists);

        List<TaskViewModel> taskViewModelList = service.findAllTasksByEmployeeId(2);

        assertEquals(1, taskViewModelList.size());

    }

    @Test
    public void findAllTasksByProjectIdAndEmployeeId() {
        when(taskRepository.findAllTasksByProjectIdAndEmployeeId(1,2)).thenReturn(otherLists);

        List<TaskViewModel> taskViewModelList = service.findAllTasksByProjectIdAndEmployeeId(1,2);

        assertEquals(1, taskViewModelList.size());

    }

    @Test
    public void findAllTasksByEmployeeIdAndName() {

        when(taskRepository.findAllTasksByEmployeeIdAndName(1,"walk")).thenReturn(mainLists);

        List<TaskViewModel> taskViewModelList = service.findAllTasksByEmployeeIdAndName(1,"walk");

        assertEquals(1, taskViewModelList.size());

    }


    @Test
    public void findAllTasksByProjectIdAndEmployeeIdAndName() {
        when(taskRepository.findAllTasksByProjectIdAndEmployeeIdAndName(1,1,"walk")).thenReturn(mainLists);

        List<TaskViewModel> taskViewModelList = service.findAllTasksByProjectIdAndEmployeeIdAndName(1,1,"walk");

        assertEquals(1, taskViewModelList.size());

    }
}