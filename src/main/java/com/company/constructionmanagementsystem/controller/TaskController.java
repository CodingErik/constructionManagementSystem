package com.company.constructionmanagementsystem.controller;

import com.company.constructionmanagementsystem.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {
    @Autowired
    TaskRepository taskRepository;

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task addTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @GetMapping("/project/task/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task getTaskById(@PathVariable Integer id) {
        Optional<Task> returlVal = taskRepository.findById(id);
        if(returnVal.isPresent())
    }

}
