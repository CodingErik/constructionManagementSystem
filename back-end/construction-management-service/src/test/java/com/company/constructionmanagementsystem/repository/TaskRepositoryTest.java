package com.company.constructionmanagementsystem.repository;

import com.company.constructionmanagementsystem.model.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaskRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TaskRepository taskRepository;


    @Before
    public void setUp() throws Exception {

        employeeRepository.deleteAll();

        projectRepository.deleteAll();

        taskRepository.deleteAll();
    }

    @Test
    public void addFindDeleteTask() {
        taskRepository.deleteAll();

        LocalDate startDate = LocalDate.now();
        LocalDate deadline = LocalDate.now();

        Task task = new Task();
        task.setName("Task One");
        task.setStartDate(startDate);
        task.setDeadline(deadline);
        task.setDescription("This is a task.");
        task.setStatus("In progress");

        // add
        task = taskRepository.save(task);

        assertEquals(1, taskRepository.findAll().size());

        // find
        Task task1 = taskRepository.findById(task.getId()).get();

        assertEquals(task1, task);

        // delete
        taskRepository.deleteById(task.getId());

        assertEquals(0, taskRepository.findAll().size());
    }

    @Test
    public void findAllTasks() {
        taskRepository.deleteAll();

        LocalDate startDate = LocalDate.now();
        LocalDate deadline = LocalDate.now();

        Task task1 = new Task();
        task1.setName("Task One");
        task1.setStartDate(startDate);
        task1.setDeadline(deadline);
        task1.setDescription("This is task1.");
        task1.setStatus("In progress");

        taskRepository.save(task1);

        Task task2 = new Task();
        task2.setName("Task One");
        task2.setStartDate(startDate);
        task2.setDeadline(deadline);
        task2.setDescription("This is task2.");
        task2.setStatus("In progress");

        taskRepository.save(task2);

        assertEquals(2, taskRepository.findAll().size());

    }

    @Test
    public void saveAndFlushTask() {
        taskRepository.deleteAll();

        LocalDate startDate = LocalDate.now();
        LocalDate deadline = LocalDate.now();

        Task task = new Task();
        task.setName("Task One");
        task.setStartDate(startDate);
        task.setDeadline(deadline);
        task.setDescription("This is a task.");
        task.setStatus("In progress");
        task = taskRepository.save(task);

        task.setStatus("Finish");

        taskRepository.saveAndFlush(task);

        Task task1 = taskRepository.findById(task.getId()).get();

        assertEquals(task1, task);
    }


    @Test
    public void findTasksByName() {
        taskRepository.deleteAll();

        LocalDate startDate = LocalDate.now();
        LocalDate deadline = LocalDate.now();

        Task task = new Task();
        task.setProjectId(1);
        task.setEmployeeId(1);
        task.setName("Task One");
        task.setStartDate(startDate);
        task.setDeadline(deadline);
        task.setDescription("This is a task.");
        task.setStatus("In progress");
        task = taskRepository.save(task);

        // findByProjectId
        List<Task> byName = taskRepository.findAllTasksByName(task.getName());

        assertEquals(1, byName.size());
        assertEquals(task, byName.get(0));
    }

    @Test
    public void findTasksByProjectId() {
        taskRepository.deleteAll();

        LocalDate startDate = LocalDate.now();
        LocalDate deadline = LocalDate.now();

        Task task = new Task();
        task.setProjectId(1);
        task.setEmployeeId(1);
        task.setName("Task One");
        task.setStartDate(startDate);
        task.setDeadline(deadline);
        task.setDescription("This is a task.");
        task.setStatus("In progress");
        task = taskRepository.save(task);

        // findByProjectId
        List<Task> byProjectId = taskRepository.findAllTasksByProjectId(1);

        assertEquals(1, byProjectId.size());
        assertEquals(task, byProjectId.get(0));

    }

    @Test
    public void findTasksByEmployeeId() {
        taskRepository.deleteAll();

        LocalDate startDate = LocalDate.now();
        LocalDate deadline = LocalDate.now();

        Task task = new Task();
        task.setProjectId(1);
        task.setEmployeeId(1);
        task.setName("Task One");
        task.setStartDate(startDate);
        task.setDeadline(deadline);
        task.setDescription("This is a task.");
        task.setStatus("In progress");
        task = taskRepository.save(task);

        // findByEmployeeId
        List<Task> byEmployeeId = taskRepository.findAllTasksByEmployeeId(1);

        assertEquals(1, byEmployeeId.size());
        assertEquals(task, byEmployeeId.get(0));

    }

    @Test
    public void findTaskByProjectIdAndEmployeeId() {
        taskRepository.deleteAll();

        LocalDate startDate = LocalDate.now();
        LocalDate deadline = LocalDate.now();

        Task task = new Task();
        task.setProjectId(1);
        task.setEmployeeId(1);
        task.setName("Task One");
        task.setStartDate(startDate);
        task.setDeadline(deadline);
        task.setDescription("This is a task.");
        task.setStatus("In progress");
        task = taskRepository.save(task);

        // findByProjectIdAndEmployeeId
        List<Task> byProjectIdAndEmployee = taskRepository.findAllTasksByProjectIdAndEmployeeId(1,1);

        assertEquals(1, byProjectIdAndEmployee.size());
        assertEquals(task, byProjectIdAndEmployee.get(0));

    }

    @Test
    public void findTasksByProjectIdAndName() {
        taskRepository.deleteAll();

        LocalDate startDate = LocalDate.now();
        LocalDate deadline = LocalDate.now();

        Task task = new Task();
        task.setProjectId(1);
        task.setEmployeeId(1);
        task.setName("Task One");
        task.setStartDate(startDate);
        task.setDeadline(deadline);
        task.setDescription("This is a task.");
        task.setStatus("In progress");
        task = taskRepository.save(task);

        // findByProjectIdAndEmployeeId
        List<Task> byProjectIdAndName = taskRepository.findAllTasksByProjectIdAndName(1,task.getName());

        assertEquals(1, byProjectIdAndName.size());
        assertEquals(task, byProjectIdAndName.get(0));
    }

    @Test
    public void findTasksByEmployeeIdAndName() {
        taskRepository.deleteAll();

        LocalDate startDate = LocalDate.now();
        LocalDate deadline = LocalDate.now();

        Task task = new Task();
        task.setProjectId(1);
        task.setEmployeeId(1);
        task.setName("Task One");
        task.setStartDate(startDate);
        task.setDeadline(deadline);
        task.setDescription("This is a task.");
        task.setStatus("In progress");
        task = taskRepository.save(task);

        // findByProjectIdAndEmployeeId
        List<Task> byEmployeeIdAndName = taskRepository.findAllTasksByEmployeeIdAndName(1,task.getName());

        assertEquals(1, byEmployeeIdAndName.size());
        assertEquals(task, byEmployeeIdAndName.get(0));
    }

    @Test
    public void findTasksByProjectIdAndEmployeeIdAndName() {
        taskRepository.deleteAll();

        LocalDate startDate = LocalDate.now();
        LocalDate deadline = LocalDate.now();

        Task task = new Task();
        task.setProjectId(1);
        task.setEmployeeId(1);
        task.setName("Task One");
        task.setStartDate(startDate);
        task.setDeadline(deadline);
        task.setDescription("This is a task.");
        task.setStatus("In progress");
        task = taskRepository.save(task);

        // findByProjectIdAndEmployeeId
        List<Task> byProjectIdAndEmployeeIdAndName = taskRepository.findAllTasksByProjectIdAndEmployeeIdAndName(1,1,task.getName());

        assertEquals(1, byProjectIdAndEmployeeIdAndName.size());
        assertEquals(task, byProjectIdAndEmployeeIdAndName.get(0));
    }
}