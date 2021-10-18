package com.example.controller;

import com.example.model.Machinery;
import com.example.repository.MachineryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MachineController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class MachineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MachineryRepository repo;

    private ObjectMapper mapper = new ObjectMapper();

    private Machinery inputMachinery;
    private String jsonMachinery;

    private Machinery outputUpdateMachinery;
    private String jsonOutputMachinery;

    @Before
    public void setUp() throws Exception {
        inputMachinery = new Machinery(1, 1000, 1000, 1000, 1000);
        jsonMachinery = mapper.writeValueAsString(inputMachinery);

        outputUpdateMachinery = new Machinery(1, 900, 900, 900, 900);
        jsonOutputMachinery = mapper.writeValueAsString(outputUpdateMachinery);
    }


    @Test
    public void helloCloud() throws Exception {

        mockMvc.perform(get("/machine"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("\"Hello this is the machine microservice\"/machines"));

    }

    @Test
    public void getMachineryInventory() throws Exception {

        given(repo.getById(1)).willReturn(inputMachinery);

        mockMvc.perform(get("/api/machinery"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonMachinery));

    }

    @Test
    public void updateMaterialAfterRetrieve() throws Exception {

        Machinery requestBodySent = new Machinery(1,100,100,100,100);
        String inputRequestBody = mapper.writeValueAsString(requestBodySent);

        Machinery expectedInventoryAfterRenting = new Machinery(1,900,900,900,900);

        given(repo.getById(1)).willReturn(inputMachinery);
        given(repo.save(expectedInventoryAfterRenting)).willReturn(outputUpdateMachinery);

        mockMvc.perform(
                        put("/api/machinery")
                                .content(inputRequestBody)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());


    }

    @Test
    public void updateReturnMachinery() throws Exception {

        Machinery requestBodySent = new Machinery(1,100,100,100,100);
        String inputRequestBody = mapper.writeValueAsString(requestBodySent);

        Machinery expectedInventoryAfterReturn = new Machinery(1,1000,1000,1000,1000);

        given(repo.getById(1)).willReturn(outputUpdateMachinery);
        given(repo.save(expectedInventoryAfterReturn)).willReturn(inputMachinery);

        mockMvc.perform(
                        put("/api/machinery/return")
                                .content(inputRequestBody)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("stock has been returned"))
                .andExpect(status().isOk());

    }

    @Test(expected = NestedServletException.class)
    public void shouldReturnNestedServletException422() throws Exception {
        Machinery inputRequestBodySent = new Machinery(10000, 100, 100, 100);
        String inputRequestBody = mapper.writeValueAsString(inputRequestBodySent);

        mockMvc.perform(
                        put("/api/machinery")
                                .content(inputRequestBody)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}