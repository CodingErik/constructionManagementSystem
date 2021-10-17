package com.company.constructionmanagementsystem.service;

import com.company.constructionmanagementsystem.model.Project;
import com.company.constructionmanagementsystem.model.Task;
import com.company.constructionmanagementsystem.repository.EmployeeRepository;
import com.company.constructionmanagementsystem.repository.ProjectRepository;
import com.company.constructionmanagementsystem.repository.TaskRepository;
import com.company.constructionmanagementsystem.security.JwtConverter;
import com.company.constructionmanagementsystem.util.LoginDetailsService;
import com.company.constructionmanagementsystem.util.feign.MachineWarehouseClient;
import com.company.constructionmanagementsystem.util.feign.MaterialWarehouseClient;
import com.company.constructionmanagementsystem.viewmodel.ProjectViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.cloud.openfeign.FeignLoggerFactory;

import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.mockito.InjectMocks;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.ExpectedCount.times;


public class ProjectServiceLayerTest {

    @Mock
    ProjectRepository projectRepository;
    @Mock
    EmployeeRepository employeeRepository;
    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    ProjectServiceLayer service;

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

    ProjectViewModel projectViewModel;

    ObjectMapper mapper = new ObjectMapper();

    Project project;
    Project otherProject;
    List<Project> projectList = new ArrayList<>();
    Task completedTask;
    java.time.LocalDate startDate;
    java.time.LocalDate deadLine;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        startDate = java.time.LocalDate.of(1999, 9, 9);
        deadLine = java.time.LocalDate.of(2010, 4, 4);

        project = new Project(1, "project", deadLine, startDate, "kitchen", true, true, new BigDecimal("100"), new BigDecimal("100"), new BigDecimal("100"), "in_progress");

        otherProject = new Project(2, "other", deadLine, startDate, "bedroom", true, true, new BigDecimal("100"),new BigDecimal("100"),new BigDecimal("100"),"in_progress");

        projectList = new ArrayList<>();

        projectList.add(project);

        completedTask = new Task(1, 1, 2, "take out the trash", startDate, deadLine, "need to take out trash", "completed");


    }

    @Test
    public void findById() {

        when(projectRepository.findById(1)).thenReturn(java.util.Optional.of(project));

        ProjectViewModel projectViewModel = service.findById(1);
        assertEquals(1, projectViewModel.getId());

    }

    @Test
    public void findAllProjects() {

        when(projectRepository.findAll()).thenReturn(projectList);

        // test
        List<ProjectViewModel> evmList = service.findAllProjects();

        assertEquals(1, evmList.size());
        verify(projectRepository, Mockito.times(1)).findAll();

    }

    @Test
    public void findByStatus() {

        when(projectRepository.findByStatus("in_progress")).thenReturn(projectList);

        List<ProjectViewModel> projectViewModel = service.findByStatus("in_progress");
        assertEquals("in_progress", projectViewModel.get(0).getStatus());
    }

    @Test
    public void findByRoomType() {

        when(projectRepository.findByRoomType("kitchen")).thenReturn(projectList);

        List<ProjectViewModel> projectViewModel = service.findByRoomType("kitchen");
        assertEquals("kitchen", projectViewModel.get(0).getRoomType());
    }

    @Test
    public void findByName() {

        when(projectRepository.findByName("project")).thenReturn(projectList);

        List<ProjectViewModel> projectViewModels = service.findByName("project");
        assertEquals("project", projectViewModels.get(0).getName());

    }

    @Test
    public void findByRoomTypeAndName() {

        when(projectRepository.findByRoomTypeAndName("kitchen", "project")).thenReturn(projectList);

        List<ProjectViewModel> projectViewModels = service.findByRoomTypeAndName("kitchen", "project");
        assertEquals("kitchen", projectViewModels.get(0).getRoomType());
        assertEquals("project", projectViewModels.get(0).getName());
    }

//    @Test
    @Test
    public void deleteProject() {

        doNothing().when(projectRepository).delete(project);

        assertEquals(0, service.findAllProjects().size());

    }

}