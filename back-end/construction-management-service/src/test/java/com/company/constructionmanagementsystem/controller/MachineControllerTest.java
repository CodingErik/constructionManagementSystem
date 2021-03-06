package com.company.constructionmanagementsystem.controller;

import com.company.constructionmanagementsystem.controller.MachineController;
import com.company.constructionmanagementsystem.exceptions.NotFoundException;
import com.company.constructionmanagementsystem.model.Machine;
import com.company.constructionmanagementsystem.repository.MachineRepository;
import com.company.constructionmanagementsystem.security.JwtConverter;
import com.company.constructionmanagementsystem.service.MachineServiceLayer;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
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
@AutoConfigureMockMvc(addFilters = false)
public class MachineControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MachineRepository repo;

    @MockBean
    MachineWarehouseClient machineWarehouseClient;

    @MockBean
    MachineServiceLayer machineServiceLayer;

    @MockBean
    JwtConverter jwtConverter;

    @MockBean
    LoginDetailsService loginDetailsService;

    Machine machine;
    List<Machine> machines;
    Machine rentMachine;
    Machine responseMachine;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {

        machine = new Machine(1, 1, 1000, 1000, 1000, 1000);
        machines = new ArrayList<>(Arrays.asList(machine));
        rentMachine = new Machine(1, 1, 100, 100, 100, 100);
        responseMachine = new Machine(1, 1, 900, 900, 900, 900);
    }

    @Test
    @WithMockUser(roles = {"admin"})
    public void getMachineWarehouseInventory() throws Exception {

        given(machineWarehouseClient.getMachineryInventory()).willReturn(machine);

        String jsonMachine = mapper.writeValueAsString(machine);

        mockMvc.perform(get("/api/machines/warehouse"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonMachine));

    }

    @Test
    @WithMockUser(roles = {"admin"})
    public void getAllMachinesInProjects() throws Exception {
        given(repo.save(machine)).willReturn(machine);
        given(repo.findAll()).willReturn(machines);

        mockMvc.perform(get("/api/machines"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"admin"})
    public void getProjectSpecificMachineInventory() throws Exception {

        given(repo.findByProjectId(machine.getId())).willReturn(java.util.Optional.ofNullable(machine));

        String jsonMachine = mapper.writeValueAsString(machine);

        mockMvc.perform(get("/api/machines/project/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonMachine));
    }

    @Test
    @WithMockUser(roles = {"admin"})
    public void rentOutMachinery() throws Exception {


        String jsoninputMachine = mapper.writeValueAsString(rentMachine);

        String returnMessage = "the following material was added to the project " + rentMachine.toString();

        given(machineServiceLayer.requestMachinery(rentMachine)).willReturn(returnMessage);


        mockMvc.perform(put("/api/machines/project/request")
                        .content(jsoninputMachine)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(returnMessage));

    }

    @Test
    @WithMockUser(roles = {"admin"})
    public void returnMachinery() throws Exception {

        String jsoninputMachine = mapper.writeValueAsString(rentMachine);

        String returnMessage = "thank you for using the machine microservice. ";

        given(machineServiceLayer.returnMachinery(rentMachine)).willReturn(returnMessage);

        mockMvc.perform(put("/api/machines/project/return")
                        .content(jsoninputMachine)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("thank you for using the machine microservice. "));

    }

    @Test
    @WithMockUser(roles = {"admin"})
    public void shouldReturn404IfProjectHasNoMachinery() throws Exception{
        mockMvc.perform(get("/api/machines/project/100"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}