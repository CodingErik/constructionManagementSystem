package com.company.constructionmanagementsystem.controller;

import com.company.constructionmanagementsystem.model.Task;
import com.company.constructionmanagementsystem.repository.ProjectRepository;
import com.company.constructionmanagementsystem.repository.TaskRepository;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    TaskRepository taskRepository;

    private ObjectMapper mapper = new ObjectMapper();

    Task inputTask1;
    Task outputTask1;
    Task inputTask2;
    Task outputTask2;
    Task inputTask3;
    Task outputTask3;
    Task inputTask4;
    Task outputTask4;

    List<Task> employee1Tasks;
    List<Task> inProgressTasksProject1;

    @Before
    public void setUp() throws Exception {

        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        inputTask1 = new Task(1, 1, 1, "Buy Lumber", LocalDate.parse("2020-10-09"), LocalDate.parse("2020-10-10"), "Go to Home Depot and pick up 5 tonnes of lumber", "In Progress");
        outputTask1 = new Task(1, 1, 1, "Buy Lumber", LocalDate.parse("2020-10-09"), LocalDate.parse("2020-10-10"), "Go to Home Depot and pick up 5 tonnes of lumber", "In Progress");

        inputTask2 = new Task(2, 1, 1, "Install Roof Panels", LocalDate.of(2020,11,1), LocalDate.of(2020,11,3), "Install Solar Power Roof Panels", "In Progress");
        outputTask2 = new Task(2, 1, 1, "Install Roof Panels", LocalDate.of(2020,11,1), LocalDate.of(2020,11,3), "Install Solar Power Roof Panels", "In Progress");

        inputTask3 = new Task(3, 2, 2, "Get Architect Plans", LocalDate.of(2020,10,8), LocalDate.of(2020,10,9), "Meet Architect Bob and get new building design", "Completed");
        outputTask3 = new Task(3, 2, 2, "Get Architect Plans", LocalDate.of(2020,10,8), LocalDate.of(2020,10,9), "Meet Architect Bob and get new building design", "Completed");

        inputTask4 = new Task(4, 1, 3, "Install Kitchen Cabinets", LocalDate.of(2021,12,31), LocalDate.of(2022,01,01), "Install Wooden Cabinets In Kitchen","In Progress");
        outputTask4 = new Task(4, 1, 3, "Install Kitchen Cabinets", LocalDate.of(2021,12,31), LocalDate.of(2022,01,01), "Install Wooden Cabinets In Kitchen","In Progress");

        employee1Tasks = new ArrayList<>(Arrays.asList(
                outputTask1, outputTask2
        ));
        inProgressTasksProject1 = new ArrayList<>(Arrays.asList(
                outputTask1, outputTask2, outputTask4
        ));
    }

    @Test
    public void shouldReturnTaskWhenPostTask() throws Exception {
        String inputJsonTask1 = mapper.writeValueAsString(inputTask1);
        String outputJsonTask1 = mapper.writeValueAsString(outputTask1);
        doReturn(outputTask1).when(taskRepository).save(inputTask1);

        mockMvc.perform(post("/api/tasks")
                .content(inputJsonTask1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJsonTask1));
    }

    @Test
    public void shouldReturnCorrectTaskWhenGetById () throws Exception {
        String outputJsonTask1 = mapper.writeValueAsString(outputTask1);
        String outputJsonTask2 = mapper.writeValueAsString(outputTask2);
        doReturn(Optional.of(outputTask1)).when(taskRepository).findById(1);
        doReturn(Optional.of(outputTask2)).when(taskRepository).findById(2);

        mockMvc.perform(get("/api/tasks?taskId=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJsonTask1));

        mockMvc.perform(get("/api/tasks?taskId=2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJsonTask2));
    }

    @Test
    public void shouldReturnAllTasksWhenGetByEmployeeId () throws Exception {
        String outputJsonTaskEmployee1 = mapper.writeValueAsString(employee1Tasks);
        doReturn(employee1Tasks).when(taskRepository).findAllTasksByEmployeeId(1);

        mockMvc.perform(get("/api/tasks/employee?employeeId=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJsonTaskEmployee1));
    }

    @Test
    public void shouldReturnAllTasksWhenGetByProjectIdAndStatus () throws Exception {
        String outputJsonTaskProject1AndStatus = mapper.writeValueAsString(inProgressTasksProject1);
        doReturn(inProgressTasksProject1).when(taskRepository).findAllTasksByProjectIdAndStatus(1, "in_progress");

        mockMvc.perform(get("/api/tasks/project/status?projectId=1&state=in_progress"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJsonTaskProject1AndStatus));
    }

    @Test
    public void shouldReturnAllTasksWhenGetByProjectIdAndName () throws Exception {
        String outputJsonTask4 = mapper.writeValueAsString(outputTask4);
        doReturn(outputTask4).when(taskRepository).findTasksByProjectIdAndName(4, "Install_Kitchen_Cabinets");

        mockMvc.perform(get("/api/tasks/project/name?projectId=4&name=Install_Kitchen_Cabinets"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJsonTask4));
    }

}
