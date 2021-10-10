package com.company.constructionmanagementsystem.controller;

import com.company.constructionmanagementsystem.model.Task;
import com.company.constructionmanagementsystem.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {
    @Autowired
    TaskRepository taskRepository;

    @PostMapping("/api/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task addTask(@RequestBody Task task) {
//        String projectName = task.getName().replaceAll(" ", "_");
//        task.setName(projectName);
//        String status = task.getStatus().replaceAll(" ", "_");
//        task.setStatus(status);
        return taskRepository.save(task);
    }

    // /api/task?id=1
    @GetMapping("/api/tasks")
    @ResponseStatus(HttpStatus.OK)
    public Task getTaskById(@RequestParam Integer taskId) {
        Optional<Task> returnVal = taskRepository.findById(taskId);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    //  getting tasks by employee Id
    // /api/tasks/employee?id=1
    @GetMapping("/api/tasks/employee")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTasksByEmployeeId(@RequestParam Integer employeeId) {
        List<Task> returnVal = taskRepository.findAllTasksByEmployeeId(employeeId);
        return returnVal;
    }

    //  getting tasks by project Id
    // /api/tasks/project?id=1
    @GetMapping("/api/tasks/project")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTasksByProjectId(@RequestParam Integer projectId) {
        List<Task> returnVal = taskRepository.findAllTasksByProjectId(projectId);
        return returnVal;
    }

    //  getting tasks by project Id and employee Id
    //  /api/tasks/project?id=1/employee?id=1
    @GetMapping("/api/tasks/project/employee")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTasksByProjectIdAndEmployeeId(@RequestParam Integer projectId, @RequestParam Integer employeeId) {
        List<Task> returnVal = taskRepository.findAllTasksByProjectIdAndEmployeeId(projectId, employeeId);
        return returnVal;
    }

    //  getting tasks by project Id and status
    //  /api/tasks/project?id=1/status?state=in_progress
    @GetMapping("/api/tasks/project/status")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTasksByProjectIdAndStatus(@RequestParam Integer projectId, @RequestParam String state) {
        List<Task> returnVal = taskRepository.findAllTasksByProjectIdAndStatus(projectId, state);
        return returnVal;
    }

    // getting tasks by projectId and name
    // /api/tasks/project/name?projectId=1&name=Buying%20Lumber
    @GetMapping("/api/tasks/project/name")
    @ResponseStatus(HttpStatus.OK)
    public Task getTaskByProjectIdAndName(@RequestParam Integer projectId, @RequestParam String name) {
        Task returnVal = taskRepository.findTasksByProjectIdAndName(projectId, name);
        return returnVal;
    }

    // GET ALL TASKS FOR DEVELOPMENT PURPOSES
    @GetMapping("/api/tasks/developmentAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTasksDevelopmentPurposes() {
        List<Task> returnVal = taskRepository.findAll();
        return returnVal;
    }
}
