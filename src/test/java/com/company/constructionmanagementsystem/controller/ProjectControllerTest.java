package com.company.constructionmanagementsystem.controller;

import com.company.constructionmanagementsystem.model.Project;
import com.company.constructionmanagementsystem.repository.EmployeeRepository;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProjectRepository projectRepository;

    private ObjectMapper mapper = new ObjectMapper();

    private Project project1;
    private Project project2;

    @MockBean
    ProjectRepository repository;

//    mapper.registerModule(new JavaTimeModule());
//
//    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);

    @Before
    public void setUp() throws Exception {

        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        //set up input
        project1.setElectric(true);
        project1.setName("project 1");
        project1.setRoomType("kitchen");
        project1.setLaborBudget(new BigDecimal("10000.000"));
        project1.setMaterialBudget(new BigDecimal("20000.000"));
//        project1.setStartDate();

        // set up input 2
        project2.setElectric(true);
        project2.setName("project 2");
        project2.setRoomType("bathroom");
        project2.setLaborBudget(new BigDecimal("50000.000"));
        project2.setMaterialBudget(new BigDecimal("40000.000"));


        //set up output
        project1.setId(1);
        project1.setElectric(true);
        project1.setName("project 1");
        project1.setRoomType("kitchen");
        project1.setLaborBudget(new BigDecimal("10000.000"));
        project1.setMaterialBudget(new BigDecimal("20000.000"));
//        project1.setDeadline();

        // set up input 2
        project1.setId(2);
        project2.setElectric(true);
        project2.setName("project 2");
        project2.setRoomType("bathroom");
        project2.setLaborBudget(new BigDecimal("50000.000"));
        project2.setMaterialBudget(new BigDecimal("40000.000"));

    }


    @Test
    public void shouldReturAllProjects() {

    }

    @Test
    public void shouldReturnProjectById() {

    }


    @Test
    public void shouldPostOneProjectToDatabase() {

    }
}