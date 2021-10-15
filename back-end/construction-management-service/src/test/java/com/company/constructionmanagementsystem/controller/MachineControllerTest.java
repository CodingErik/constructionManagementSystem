package com.company.constructionmanagementsystem.controller;

import com.company.constructionmanagementsystem.controller.MachineController;
import com.company.constructionmanagementsystem.model.Machine;
import com.company.constructionmanagementsystem.repository.MachineRepository;
import com.company.constructionmanagementsystem.security.JwtConverter;
import com.company.constructionmanagementsystem.util.LoginDetailsService;
import com.company.constructionmanagementsystem.util.feign.MachineWarehouseClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MachineController.class)
//@AutoConfigureMockMvc(addFilters = false)
public class MachineControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MachineRepository repo;

    @MockBean
    MachineWarehouseClient machineWarehouseClient;

    @MockBean
    JwtConverter jwtConverter;

    @MockBean
    LoginDetailsService loginDetailsService;

    Machine machine;
    Machine rentMachine;
    Machine responseMachine;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {

         machine = new Machine(1, 1, 1000, 1000, 1000, 1000);
         rentMachine = new Machine(1, 1, 100, 100, 100, 100);
         responseMachine = new Machine(1, 1, 900, 900, 900, 900);
    }

    @Test
    public void getAllInventory() throws Exception {

        given(machineWarehouseClient.getMachineryInventory()).willReturn(machine);

        String jsonMachine = mapper.writeValueAsString(machine);

        mockMvc.perform(get("/api/machine/inventory"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonMachine));

    }

    @Test
    public void getProjectSpecificMachineInventory() throws Exception{

        given(repo.findByProjectId(machine.getId())).willReturn(java.util.Optional.ofNullable(machine));

        String jsonMachine = mapper.writeValueAsString(machine);

        mockMvc.perform(get("/api/machine/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonMachine));
    }

    @Test
    public void rentOutMachinery() throws Exception {

        given(repo.save(machine)).willReturn(machine);

        String jsoninputMachine = mapper.writeValueAsString(rentMachine);

        mockMvc.perform(post("/api/machine/request")
                        .content(jsoninputMachine)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(content().string("the following material was added to the project " + rentMachine.toString()));

    }

    @Test
    public void returnMachinery() throws Exception{

       doNothing().when(repo).deleteAll();


        String jsoninputMachine = mapper.writeValueAsString(rentMachine);

        mockMvc.perform(post("/api/machine/return")
                        .content(jsoninputMachine)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(content().string("thank you for using the machine microservice. "));

    }


}