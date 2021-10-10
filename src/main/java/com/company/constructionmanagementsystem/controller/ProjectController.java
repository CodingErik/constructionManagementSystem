package com.company.constructionmanagementsystem.controller;


import com.company.constructionmanagementsystem.model.Project;
import com.company.constructionmanagementsystem.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ProjectController {

    @Autowired
    ProjectRepository repo;

    @PostMapping("/api/project/")
    @ResponseStatus(HttpStatus.CREATED)
    public Project addProject(@RequestBody Project project) {
        return repo.save(project);
    }

    /** request for filtering fits this category more */
    // /api/project?isPlumbing=isElectric= refactor
    @GetMapping("/api/projects")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Project> getAllProjects(@RequestParam(required = false) boolean isPlumbing, @RequestParam(required = false) boolean isElectric) {

        List<Project> returnList = repo.findAll();

        if (isPlumbing) {
            returnList = returnList.stream()
                    .filter(project -> project.isPlumbing())
                    .collect(Collectors.toList());
        }

        if (isElectric) {
            returnList = returnList.stream()
                    .filter(project -> project.isElectric())
                    .collect(Collectors.toList());
        }

        return returnList;
    }


    // /api/project?id=1 refactor
    @GetMapping("/api/project/id/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Project getProjectsById(@PathVariable Integer id) {
        Project project = repo.getById(id);
//        if (returnVal.isPresent()) {
//            return returnVal.get();
//        } else {
//            return null;
//        }
        return project;
    }

    // /api/project/deadline/{deadline}
    @GetMapping("/api/project/deadline/{deadline}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Project> findByDeadline(@PathVariable LocalDate deadline){
        List<Project> project = repo.findByDeadline(deadline);
        return project;
    }

    // /api/project/status/{deadline}
    @GetMapping("/api/project/status/{status}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Project> findByStatus(@PathVariable String status){

        List<Project> project = repo.findByStatus(status);
        return project;
    }


    @GetMapping("/api/project/roomType/{roomType}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Project> findProjectsByRoomType(@PathVariable String roomType){
        return repo.findByRoomType(roomType);
    }

}
