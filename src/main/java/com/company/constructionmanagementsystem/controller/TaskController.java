package com.company.constructionmanagementsystem.controller;

import com.company.constructionmanagementsystem.model.Task;
import com.company.constructionmanagementsystem.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {
    @Autowired
    TasksRepository taskRepository;

    @PostMapping("/api/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task addTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    // /api/task?id=1
    @GetMapping("/api/tasks")
    @ResponseStatus(HttpStatus.OK)
    public Task getTaskById(@RequestParam Integer id) {
        Optional<Task> returnVal = taskRepository.findById(id);
        if(returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }



    //  getting tasks by employee Id
    // /api/tasks/employee?id=1
    @GetMapping("/api/tasks/employee")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getTasksByEmployeeId(@RequestParam Integer id)
    {
        List<Task> returnVal = taskRepository.findByEmployeeId(id);
        return returnVal;
    }

    //  getting tasks by project Id
    // /api/tasks/project?id=1
    @GetMapping("/api/tasks/project")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getTasksByProjectId(@RequestParam Integer id)
    {
        List<Task> returnVal = taskRepository.findByProjectId(id);
        return returnVal;
    }

    //  getting tasks by project Id and employee Id
    //  /api/tasks/project?id=1/employee?id=1
    @GetMapping("/api/tasks/project/employee")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getTasksByProjectIdAndEmployeeId(@RequestParam Integer projectId, @RequestParam Integer employeeId)
    {
        List<Task> returnVal = taskRepository.findByProjectIdAndEmployeeId(projectId, employeeId);
        return returnVal;
    }




}
