package com.company.repository;

import com.company.model.Material;
import com.company.repository.MaterialRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Transient;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MaterialRepositoryTest {

    @Autowired
    MaterialRepository repo;

    @Before
    public void setUp() throws Exception {

        List<Material> material = repo.findAll();

        material.stream()
                .forEach(material1 -> repo.delete(material1));

    }

    @Test
    @Transactional
//    @Transient
    public void addGetByIdDeleteMachineryInventory() {
        Material materialInventory = new Material(1, 1000, 1000, 1000, 1000);

        repo.save(materialInventory);
        Material fromDatabase = repo.getById(1);
        assertEquals(materialInventory, fromDatabase);

        repo.deleteById(1);
        assertEquals(0,repo.findAll().size());

    }

    @Test
    @Transactional
    public void getAllInventory() {
        Material materialInventory = new Material(1, 1000, 1000, 1000, 1000);
        repo.save(materialInventory);

        List<Material> materialList = repo.findAll();
        assertEquals(1, materialList.size());
    }

    @Test
    @Transactional
    public void getByIdAndSaveInvetory() {
        Material materialInventory = new Material(1, 1000, 1000, 1000, 1000);
        repo.save(materialInventory);

        Material fromDatabase = repo.getById(1);
        fromDatabase.setBrick(900);
        repo.save(fromDatabase);
        assertEquals(900, repo.getById(1).getBrick());

    }
}