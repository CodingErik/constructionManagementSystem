package com.company.constructionmanagementsystem.controller;


import com.company.constructionmanagementsystem.model.Project;
import com.company.constructionmanagementsystem.repository.ProjectRepository;
import com.company.constructionmanagementsystem.service.ProjectServiceLayer;
import com.company.constructionmanagementsystem.viewmodel.ProjectViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class ProjectController {

    @Autowired
    ProjectRepository repo;

    @Autowired
    ProjectServiceLayer projectServiceLayer;

    @PostMapping("/api/projects")
    @ResponseStatus(HttpStatus.CREATED)
    public Project addProject(@RequestBody Project project) {
        return repo.save(project);
    }

    /** request for filtering fits this category more */
    // /api/project?isPlumbing=isElectric= refactor
    @GetMapping("/api/projects")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ProjectViewModel> getAllProjects(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String roomType
            ) {

        List<ProjectViewModel> returnList = projectServiceLayer.findAllProjects();

        if (name != null) {
            returnList = projectServiceLayer.findByName(name);
        }

        if (roomType != null){
            returnList = projectServiceLayer.findByRoomType(roomType);
        }

        if (name != null && roomType != null){
            returnList = projectServiceLayer.findByRoomTypeAndName(roomType, name);
        }

        return returnList;
    }

    @PutMapping("/api/projects")
    @ResponseStatus(value=HttpStatus.NO_CONTENT)
    public void putProject(@RequestBody Project project) throws Exception{
        repo.save(project);
    }


    @GetMapping("/api/projects/id/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ProjectViewModel getProjectsById(@PathVariable Integer id) {

        ProjectViewModel pvm = projectServiceLayer.findById(id);

        return pvm;
    }

    @GetMapping("/api/projects/deadline/{deadline}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ProjectViewModel> findByDeadline(@PathVariable LocalDate deadline){
        return projectServiceLayer.findByDeadline(deadline);
    }

    @GetMapping("/api/projects/startDate/{startDate}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ProjectViewModel> findByStartDate(@PathVariable LocalDate startDate){
        return projectServiceLayer.findByStartDate(startDate);
    }

    @GetMapping("/api/projects/status/{status}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ProjectViewModel> findByStatus(@PathVariable String status){
        /** maybe figure out 3 different statuses
         *  maybe somekind of switch statement
         * */

        return projectServiceLayer.findByStatus(status);
    }

    @GetMapping("/api/projects/roomType/{roomType}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ProjectViewModel> findProjectsByRoomType(@PathVariable String roomType) throws Exception {
        List<ProjectViewModel> returnProjectList = projectServiceLayer.findByRoomType(roomType);

        return returnProjectList;
    }

    @GetMapping("/api/projects/name/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ProjectViewModel> findProjectByName(@PathVariable String name) throws Exception {
        List<ProjectViewModel> returnProjectList = projectServiceLayer.findByName(name);

        return returnProjectList;
    }

    @GetMapping("/api/projects/roomType/{roomType}/name/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ProjectViewModel> findProjectByRoomTypeAndName(@PathVariable String roomType, @PathVariable String name) throws Exception {
        List<ProjectViewModel> returnProjectList = projectServiceLayer.findByRoomTypeAndName(roomType,name);

        return returnProjectList;
    }

}
