package com.company.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.company.model.Material;
import com.company.repository.MaterialRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

@RunWith(SpringRunner.class)
@WebMvcTest(MaterialController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class MaterialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MaterialRepository repo;

    private ObjectMapper mapper = new ObjectMapper();

    private Material material;
    private String jsonMaterial;

    private Material outputUpdateMaterial;
    private String jsonOutputMaterial;

    @Before
    public void setUp() throws Exception {
        material = new Material(1, 1000, 1000, 1000, 1000);
        jsonMaterial = mapper.writeValueAsString(material);

        outputUpdateMaterial = new Material(1, 900, 900, 900, 900);
        jsonOutputMaterial = mapper.writeValueAsString(outputUpdateMaterial);
    }

    @Test
    public void helloCloud() throws Exception {
        mockMvc.perform(get("/material"))
                .andExpect(status().isOk())
                .andExpect(content().string("\"Hello this is the warehouse microservice\"/materials"));

    }

    @Test
    public void getWarehouseInventory() throws Exception {

        given(repo.getById(1)).willReturn(material);

        mockMvc.perform(get("/api/material"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonMaterial));

    }

    @Test
    public void updateMaterialAfterRetrieve() throws Exception {
        Material inputRequestBodySent = new Material(1, 100, 100, 100, 100);
        String inputRequestBody = mapper.writeValueAsString(inputRequestBodySent);

        Material expectedUpdatedInventory = new Material(1, 900, 900, 900, 900);

        given(repo.getById(1)).willReturn(material);
        given(repo.save(expectedUpdatedInventory)).willReturn(outputUpdateMaterial);

        mockMvc.perform(
                        put("/api/material")
                                .content(inputRequestBody)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }


    @Test
    public void updateMaterialRefill() throws Exception {

        given(repo.getById(1)).willReturn(material);

        mockMvc.perform(
                        put("/api/material/refill"))
                .andExpect(status().isOk());
    }


    @Test(expected = NestedServletException.class)
    public void shouldReturnNestedServletException422() throws Exception {
        Material inputRequestBodySent = new Material(10000, 100, 100, 100);
        String inputRequestBody = mapper.writeValueAsString(inputRequestBodySent);

        mockMvc.perform(
                        put("/api/material")
                                .content(inputRequestBody)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}