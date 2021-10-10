package com.company.constructionmanagementsystem.controller;

import com.company.constructionmanagementsystem.model.Project;
import com.company.constructionmanagementsystem.repository.ProjectRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProjectRepository repo;

    private ObjectMapper mapper = new ObjectMapper();

    private Project project1Input;
    private Project project1Output;

    @Before
    public void setUp() throws Exception {

        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        project1Input = new Project();
        project1Output = new Project();

        //set up input
        project1Input.setElectric(true);
        project1Input.setName("project 1");
        project1Input.setStatus("inProgress");
        project1Input.setRoomType("kitchen");
        project1Input.setLaborBudget(new BigDecimal("10000.000"));
        project1Input.setMaterialBudget(new BigDecimal("20000.000"));
        project1Input.setTotalBudget(new BigDecimal(30000));
        project1Input.setDeadline(LocalDate.of(2021, 10, 22));
        project1Input.setStartDate(LocalDate.of(2021, 10, 10));


        //set up output
        project1Output.setId(1);
        project1Output.setElectric(true);
        project1Output.setName("project 1");
        project1Input.setStatus("inProgress");
        project1Output.setRoomType("kitchen");
        project1Output.setLaborBudget(new BigDecimal("10000.000"));
        project1Output.setMaterialBudget(new BigDecimal("20000.000"));
        project1Output.setTotalBudget(new BigDecimal(30000));
        project1Output.setDeadline(LocalDate.of(2021, 10, 22));
        project1Output.setStartDate(LocalDate.of(2021, 10, 10));


    }


    @Test
    public void shouldReturnPostProject() throws Exception {

        String jsonInput = mapper.writeValueAsString(project1Input);
        String jsonOutput = mapper.writeValueAsString(project1Output);

        given(repo.save(project1Input)).willReturn(project1Output);

        mockMvc.perform(post("/api/project/")
                        .content(jsonInput)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(content().json(jsonOutput))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnProjectById() throws Exception {

        String jsonOutput = mapper.writeValueAsString(project1Output);

        given(repo.getById(project1Output.getId())).willReturn(project1Output);

        mockMvc.perform(get("/api/project/id/1"))
                .andExpect(content().json(jsonOutput))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldReturnProjectsByStatus() throws Exception {

        List<Project> projectsList = new ArrayList<>();

        projectsList.add(project1Output);

        String jsonOutput = mapper.writeValueAsString(projectsList);

        given(repo.findByStatus("inProgress")).willReturn(projectsList);

        mockMvc.perform(get("/api/project/status/inProgress"))
                .andExpect(content().json(jsonOutput))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldReturnProjectsByRoomType() throws Exception {
//        findByRoomType

        List<Project> projectsList = new ArrayList<>();

        projectsList.add(project1Output);

        String jsonOutput = mapper.writeValueAsString(projectsList);

        given(repo.findByRoomType("kitchen")).willReturn(projectsList);

        mockMvc.perform(get("/api/project/roomType/kitchen"))
                .andExpect(content().json(jsonOutput))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnProjectByName() {

    }
}