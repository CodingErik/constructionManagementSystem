package com.company.constructionmanagementsystem.controller;

import com.company.constructionmanagementsystem.repository.ProjectRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProjectRepository projectRepository;

    private ObjectMapper mapper = new ObjectMapper();



    @Before
    public void setUp() throws Exception {
    }



}