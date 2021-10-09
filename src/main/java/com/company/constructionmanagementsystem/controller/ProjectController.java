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

    @RequestMapping(value = "/api/projects", method = RequestMethod.GET)
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


    // /api/project?id=1
    @GetMapping("/api/project")
    @ResponseStatus(value = HttpStatus.OK)
    public Project getProjectsById(@RequestParam Integer id) {
        Optional<Project> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    // /api/project/{deadline}
    @GetMapping("/api/project/{deadline}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Project> findByDeadline(@PathVariable LocalDate deadline){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
//        System.out.println(formatter.format(deadline));

        List<Project> project = repo.findByDeadline(deadline);
        return project;
    }

}
