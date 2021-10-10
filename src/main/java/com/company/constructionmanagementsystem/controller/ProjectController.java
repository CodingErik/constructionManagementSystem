package com.company.constructionmanagementsystem.controller;


import com.company.constructionmanagementsystem.model.Project;
import com.company.constructionmanagementsystem.repository.ProjectRepository;
import com.company.constructionmanagementsystem.service.ProjectServiceLayer;
import com.company.constructionmanagementsystem.viewmodel.ProjectViewModel;
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

    @Autowired
    ProjectServiceLayer projectServiceLayer;

    @PostMapping("/api/project/")
    @ResponseStatus(HttpStatus.CREATED)
    public Project addProject(@RequestBody Project project) {
        return repo.save(project);
    }
    /** after change */
        // /api/project?isPlumbing=isElectric= refactor
    @RequestMapping(value = "/api/projects", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<ProjectViewModel> getAllProjects(@RequestParam(required = false) boolean isPlumbing, @RequestParam(required = false) boolean isElectric) {

        List<ProjectViewModel> returnList = projectServiceLayer.findAllProjects();

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
    /** before change*/
//    /** request for filtering fits this category more */
//    // /api/project?isPlumbing=isElectric= refactor
//    @RequestMapping(value = "/api/projects", method = RequestMethod.GET)
//    @ResponseStatus(value = HttpStatus.OK)
//    public List<Project> getAllProjects(@RequestParam(required = false) boolean isPlumbing, @RequestParam(required = false) boolean isElectric) {
//
//        List<Project> returnList = repo.findAll();
//
//        if (isPlumbing) {
//            returnList = returnList.stream()
//                    .filter(project -> project.isPlumbing())
//                    .collect(Collectors.toList());
//        }
//
//        if (isElectric) {
//            returnList = returnList.stream()
//                    .filter(project -> project.isElectric())
//                    .collect(Collectors.toList());
//        }
//
//        return returnList;
//    }

    /** after change */
    @GetMapping("/api/project/id/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ProjectViewModel getProjectsById(@PathVariable Integer id) {

        ProjectViewModel pvm = projectServiceLayer.findById(id);

        return pvm;
    }

    // /api/project?id=1 refactor
    /**
    * before change
    * */
//    @GetMapping("/api/project/id/{id}")
//    @ResponseStatus(value = HttpStatus.OK)
//    public Project getProjectsById(@PathVariable Integer id) {
//        Optional<Project> returnVal = repo.findById(id);
//        if (returnVal.isPresent()) {
//            return returnVal.get();
//        } else {
//            return null;
//        }
//    }

    /** after change */
    @GetMapping("/api/project/deadline/{deadline}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ProjectViewModel> findByDeadline(@PathVariable LocalDate deadline){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
//        System.out.println(formatter.format(deadline));
        return projectServiceLayer.findByDeadline(deadline);
    }
    /** before change*/
    // /api/project/deadline/{deadline}
//    @GetMapping("/api/project/deadline/{deadline}")
//    @ResponseStatus(value = HttpStatus.OK)
//    public List<Project> findByDeadline(@PathVariable LocalDate deadline){
////        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
////        System.out.println(formatter.format(deadline));
//
//        List<Project> project = repo.findByDeadline(deadline);
//        return project;
//    }
    /** after change */
    // /api/project/status/{status}
    @GetMapping("/api/project/status/{status}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ProjectViewModel> findByStatus(@PathVariable String status){

        /** maybe figure out 3 different statuses
         *  maybe somekind of switch statement
         * */

        return projectServiceLayer.findByStatus(status);
    }

    /** before change*/
//    // /api/project/status/{deadline}
//    @GetMapping("/api/project/status/{status}")
//    @ResponseStatus(value = HttpStatus.OK)
//    public List<Project> findByStatus(@PathVariable String status){
//
//        /** maybe figure out 3 different statuses
//         *  maybe somekind of switch statement
//         * */
//
//        List<Project> project = repo.findByStatus(status);
//        return project;
//    }


}
