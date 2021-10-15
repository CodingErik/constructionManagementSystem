package com.company.constructionmanagementsystem.controller;

import com.company.constructionmanagementsystem.model.Task;
import com.company.constructionmanagementsystem.repository.TaskRepository;
import com.company.constructionmanagementsystem.service.TaskServiceLayer;
import com.company.constructionmanagementsystem.viewmodel.TaskViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RefreshScope
public class TaskController {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    TaskServiceLayer taskServiceLayer;

    @PostMapping("/api/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task addTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @GetMapping("/api/tasks/id")
    @ResponseStatus(HttpStatus.OK)
    public TaskViewModel getTaskById(@RequestParam Integer taskId) {
        return taskServiceLayer.findTaskById(taskId);
    }


    @GetMapping("/api/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskViewModel> getAllTasks(
            @RequestParam(required = false) Integer projectId,
            @RequestParam(required = false) Integer employeeId,
            @RequestParam(required = false) String name
    ) {

        List<TaskViewModel> returnList = taskServiceLayer.findAllTasks();

        if (name != null) {
            returnList = taskServiceLayer.findTasksByName(name);
        }

        if (projectId != null) {
            returnList = taskServiceLayer.findAllTasksByProjectId(projectId);
        }

        if (employeeId != null) {
            returnList = taskServiceLayer.findAllTasksByEmployeeId(employeeId);
        }

        if (projectId != null && employeeId != null) {
            returnList = taskServiceLayer.findAllTasksByProjectIdAndEmployeeId(projectId, employeeId);
        }

        if (projectId != null && name != null) {
            returnList = taskServiceLayer.findAllTasksByProjectIdAndName(projectId, name);
        }

        if (employeeId != null && name != null) {
            returnList = taskServiceLayer.findAllTasksByEmployeeIdAndName(employeeId, name);
        }

        if (projectId != null && employeeId != null && name != null) {
            returnList = taskServiceLayer.findAllTasksByProjectIdAndEmployeeIdAndName(projectId, employeeId, name);
        }

        return returnList;
    }

    @PutMapping("/api/tasks")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTask(@RequestBody Task task) {
        taskRepository.save(task);
    }


//
//    //  getting tasks by employee Id
//    // /api/tasks/employee?id=1
//    @GetMapping("/api/tasks/employee")
//    @ResponseStatus(HttpStatus.OK)
//    public List<TaskViewModel> getAllTasksByEmployeeId(@RequestParam Integer employeeId) {
////        List<Task> returnVal = taskRepository.findAllTasksByEmployeeId(employeeId);
//        List<TaskViewModel> returnVal = taskServiceLayer.findAllTasksByEmployeeId(employeeId);
//        return returnVal;
//    }
//
//    //  getting tasks by project Id
//    // /api/tasks/project?id=1
//    @GetMapping("/api/tasks/project")
//    @ResponseStatus(HttpStatus.OK)
//    public List<Task> getAllTasksByProjectId(@RequestParam Integer projectId) {
//        List<Task> returnVal = taskRepository.findAllTasksByProjectId(projectId);
//        return returnVal;
//    }
//
//    //  getting tasks by project Id and employee Id
//    //  /api/tasks/project?id=1/employee?id=1
//    @GetMapping("/api/tasks/project/employee")
//    @ResponseStatus(HttpStatus.OK)
//    public List<Task> getAllTasksByProjectIdAndEmployeeId(@RequestParam Integer projectId, @RequestParam Integer employeeId) {
//        List<Task> returnVal = taskRepository.findAllTasksByProjectIdAndEmployeeId(projectId, employeeId);
//        return returnVal;
//    }
//
//    //  getting tasks by project Id and status
//    //  /api/tasks/project?id=1/status?state=in_progress
//    @GetMapping("/api/tasks/project/status")
//    @ResponseStatus(HttpStatus.OK)
//    public List<Task> getAllTasksByProjectIdAndStatus(@RequestParam Integer projectId, @RequestParam String state) {
//        List<Task> returnVal = taskRepository.findAllTasksByProjectIdAndStatus(projectId, state);
//        return returnVal;
//    }
//
//    // getting tasks by projectId and name
//    // /api/tasks/project/name?projectId=1&name=Buying%20Lumber
//    @GetMapping("/api/tasks/project/name")
//    @ResponseStatus(HttpStatus.OK)
//    public List<TaskViewModel> getTaskByProjectIdAndName(@RequestParam Integer projectId, @RequestParam String name) {
//        List<TaskViewModel> returnVal = taskServiceLayer.findTasksByProjectIdAndName(projectId, name);
//
//        return returnVal;
//    }
//
//    // GET ALL TASKS FOR DEVELOPMENT PURPOSES
//    @GetMapping("/api/tasks/all")
//    @ResponseStatus(HttpStatus.OK)
//    public List<Task> getAllTasksDevelopmentPurposes() {
//        List<Task> returnVal = taskRepository.findAll();
//        return returnVal;
//    }
}
