package com.company.constructionmanagementsystem.repository;

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
public class MaterialRepositoryTest {

    @Autowired
    MaterialRepository materialRepository;

    @Autowired
    MachineRepository machineRepository;

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
        materialRepository.deleteAll();

        Material material = new Material();
        material.setProjectId(1);
        material.setSteel(200);
        material.setBrick(200);
        material.setLumber(200);
        material.setCement(200);

        // add
        material = materialRepository.save(material);

        assertEquals(1, materialRepository.findAll().size());

        // find

        Material material1 = materialRepository.findById(material.getId()).get();

        assertEquals(material1, material);

        // delete
        materialRepository.deleteById(material.getId());

        assertEquals(0, materialRepository.findAll().size());

    }

    @Test
    public void saveAndFlushMachine() {
        materialRepository.deleteAll();

        Material material = new Material();
        material.setProjectId(1);
        material.setSteel(200);
        material.setBrick(200);
        material.setLumber(200);
        material.setCement(200);
        material = materialRepository.save(material);

        material.setCement(2000);
        material.setLumber(1000);

        materialRepository.saveAndFlush(material);

        Material material1 = materialRepository.findById(material.getId()).get();

        assertEquals(material1, material);


    }

    @Test
    public void findByProjectId() {
        materialRepository.deleteAll();


        Material material = new Material();
        material.setProjectId(1);
        material.setSteel(200);
        material.setBrick(200);
        material.setLumber(200);
        material.setCement(200);

        material = materialRepository.save(material);

        // findByProjectId
        Material byProjectId = materialRepository.findByProjectId(1).get();

        assertEquals(material, byProjectId);

    }

}