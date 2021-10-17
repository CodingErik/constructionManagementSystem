package com.company.constructionmanagementsystem.repository;

import com.company.constructionmanagementsystem.model.Machine;
import com.company.constructionmanagementsystem.model.Material;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MachineRepositoryTest {
    @Autowired
    MachineRepository machineRepository;

    @Autowired
    MaterialRepository materialRepository;


    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TaskRepository taskRepository;

    @Before
    public void setUp() throws Exception {
        materialRepository.deleteAll();

        machineRepository.deleteAll();

        employeeRepository.deleteAll();

        projectRepository.deleteAll();

        taskRepository.deleteAll();
    }

    @Test
    public void addFindDeleteMaterial() {
        machineRepository.deleteAll();

        Machine machine = new Machine();
        machine.setProjectId(1);
        machine.setCrane(50);
        machine.setForklift(50);
        machine.setLadder(50);
        machine.setDrill(50);

        // add
        machine = machineRepository.save(machine);

        assertEquals(1, machineRepository.findAll().size());

        // find

        Machine machine1 = machineRepository.findById(machine.getId()).get();

        assertEquals(machine1, machine);

        // delete
        machineRepository.deleteById(machine.getId());

        assertEquals(0, machineRepository.findAll().size());

    }

    @Test
    public void saveAndFlushMachine() {
        machineRepository.deleteAll();

        Machine machine = new Machine();
        machine.setProjectId(1);
        machine.setCrane(50);
        machine.setForklift(50);
        machine.setLadder(50);
        machine.setDrill(50);

        machine = machineRepository.save(machine);

        machine.setDrill(40);
        machine.setForklift(10);


        machineRepository.saveAndFlush(machine);

        Machine machine1 = machineRepository.findById(machine.getId()).get();

        assertEquals(machine1, machine);


    }

    @Test
    public void findByProjectId() {
        machineRepository.deleteAll();


        Machine machine = new Machine();
        machine.setProjectId(1);
        machine.setCrane(50);
        machine.setForklift(50);
        machine.setLadder(50);
        machine.setDrill(50);

        machine = machineRepository.save(machine);

        // findByProjectId
        Machine byProjectId = machineRepository.findByProjectId(1).get();

        assertEquals(machine, byProjectId);

    }
}