package com.example.repository;

import com.example.model.Machinery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MachineryRepositoryTest {

    @Autowired
    MachineryRepository repo;

    @Before
    public void setUp() throws Exception {

        List<Machinery> machinery = repo.findAll();

        machinery.stream()
                .forEach(machinery1 -> repo.delete(machinery1));

    }

    @Test
    @Transactional
//    @Transient
    public void addGetByIdDeleteMachineryInventory() {
        Machinery machineryInventory = new Machinery(1, 1000, 1000, 1000, 1000);

        repo.save(machineryInventory);
        Machinery fromDatabase = repo.getById(1);
        assertEquals(machineryInventory, fromDatabase);

        repo.deleteById(1);
        assertEquals(0,repo.findAll().size());

    }

    @Test
    @Transactional
    public void getAllInventory() {
        Machinery machineryInventory = new Machinery(1, 1000, 1000, 1000, 1000);
        repo.save(machineryInventory);

        List<Machinery> machineryList = repo.findAll();
        assertEquals(1, machineryList.size());
    }

    @Test
    @Transactional
    public void getByIdAndSaveInvetory() {
        Machinery machineryInventory = new Machinery(1, 1000, 1000, 1000, 1000);
        repo.save(machineryInventory);

        Machinery fromDatabase = repo.getById(1);
        fromDatabase.setDrill(900);
        repo.save(fromDatabase);
        assertEquals(900, repo.getById(1).getDrill());

    }
}