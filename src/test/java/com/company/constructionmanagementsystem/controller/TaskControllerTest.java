package com.company.constructionmanagementsystem.controller;

import com.company.constructionmanagementsystem.repository.ProjectRepository;
import com.company.constructionmanagementsystem.repository.TaskRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectController.class)
public class TaskControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    TaskRepository taskRepository;


}
