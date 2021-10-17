package com.company.constructionmanagementsystem.controller;

import com.company.constructionmanagementsystem.model.Material;
import com.company.constructionmanagementsystem.model.Project;
import com.company.constructionmanagementsystem.repository.MaterialRepository;
import com.company.constructionmanagementsystem.repository.ProjectRepository;
import com.company.constructionmanagementsystem.security.JwtConverter;
import com.company.constructionmanagementsystem.service.MaterialServiceLayer;
import com.company.constructionmanagementsystem.util.LoginDetailsService;
import com.company.constructionmanagementsystem.util.feign.MachineWarehouseClient;
import com.company.constructionmanagementsystem.util.feign.MaterialWarehouseClient;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MaterialController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class MaterialControllerTest {

    @MockBean
    MaterialRepository materialRepository;

    @MockBean
    ProjectRepository projectRepository;

    @MockBean
    MaterialServiceLayer materialServiceLayer;

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

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    Project project1;
    Project project2;
    List<Project> projectList;

    Material material1;
    Material material2;
    List<Material> materialList;


    @Before
    public void setUp() throws Exception {
        java.time.LocalDate deadline = java.time.LocalDate.of(2010,4 ,4);
        java.time.LocalDate startDate = java.time.LocalDate.now();
        MathContext mathContext = new MathContext(4);

        project1 = new Project(1,"Project1",deadline,startDate,"Kitchen",true,true,new BigDecimal(9800.34).round(mathContext),new BigDecimal(3480.16).round(mathContext),new BigDecimal(13280.50).round(mathContext),"in_progress");
        project2 = new Project(2,"Project2",deadline,startDate,"Living Room",false,true,new BigDecimal(9000.50).round(mathContext),new BigDecimal(9000.50).round(mathContext),new BigDecimal(18001.00).round(mathContext),"completed");
        projectList = new ArrayList<>();
        projectList.add(project1);
        projectList.add(project2);

        material1 = new Material(1,1,200,150,200,500);
        material2 = new Material(2,2,200,400,500,100);
        materialList = new ArrayList<>();
        materialList.add(material1);
        materialList.add(material2);
    }

    @Test
    @WithMockUser(roles = {"admin"})
    public void shouldCreateANewMaterialReference() throws Exception {

        String jsonInputMaterial = mapper.writeValueAsString(material1);

        String returnJson = "the following material was added to the project " + material1.toString();

        given(materialServiceLayer.requestMaterials(material1)).willReturn(returnJson);

        mockMvc.perform(
                post("/api/materials/project/request")
                        .content(jsonInputMaterial)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(returnJson));
    }

    @Test
    public void shouldReturnMaterialReferenceWithCorrectProjectId() throws Exception {
        projectRepository.deleteAll();
        materialRepository.deleteAll();

        projectRepository.save(project1);
        projectRepository.save(project2);

        materialRepository.save(material1);
        materialRepository.save(material2);

        Material outputMaterial = new Material(1,1,200,150,200,500);

        String outputJson = mapper.writeValueAsString(outputMaterial);

        given(materialRepository.findByProjectId(project1.getId())).willReturn(java.util.Optional.ofNullable(outputMaterial));

        mockMvc.perform(get("/api/materials/project/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnAllMaterialsLeftInStock() throws Exception {
        Material outputMaterial = new Material(null,null,1000,1000,1000,1000);

        List<Material> outputMaterials = new ArrayList<>();
        outputMaterials.add(outputMaterial);

        String outputJson = mapper.writeValueAsString(outputMaterials);

        given(materialRepository.findAll()).willReturn(outputMaterials);

        mockMvc.perform(get("/api/materials"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldRefillMaterials() throws Exception {
        given(materialWarehouseClient.updateMaterialRefill()).willReturn("stock has been refilled");
    }
}