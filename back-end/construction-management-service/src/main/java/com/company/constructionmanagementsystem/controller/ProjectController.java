package com.company.constructionmanagementsystem.controller;


import com.company.constructionmanagementsystem.model.Machine;
import com.company.constructionmanagementsystem.model.Project;
import com.company.constructionmanagementsystem.repository.MachineRepository;
import com.company.constructionmanagementsystem.repository.MaterialRepository;
import com.company.constructionmanagementsystem.repository.ProjectRepository;
import com.company.constructionmanagementsystem.service.ProjectServiceLayer;
import com.company.constructionmanagementsystem.util.feign.MachineWarehouseClient;
import com.company.constructionmanagementsystem.model.Material;

import com.company.constructionmanagementsystem.util.feign.MaterialWarehouseClient;
import com.company.constructionmanagementsystem.viewmodel.ProjectViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RefreshScope
public class ProjectController {

    @Autowired
    ProjectRepository repo;

    @Autowired
    MachineRepository machineRepository;
    @Autowired
    MaterialRepository materialRepository;

    @Autowired
    ProjectServiceLayer projectServiceLayer;

    @Autowired
    private final MachineWarehouseClient machineWarehouseClient;

    @Autowired
    private final MaterialWarehouseClient materialWarehouseClient;

    public ProjectController(MachineWarehouseClient machineWarehouseClient, MaterialWarehouseClient materialWarehouseClient) {
        this.machineWarehouseClient = machineWarehouseClient;
        this.materialWarehouseClient = materialWarehouseClient;
    }


    @PostMapping("/api/projects")
    @ResponseStatus(HttpStatus.CREATED)
    public Project addProject(@RequestBody Project project) {
        /**     CREATE
         * use feign with projectViewModel material object */
        /** materialWarehouseClient.updateMaterialAfterRetrieve(projectViewModel.material);
         * machineWarehouseClient.returnMachinery(machinery);
         * */
        Project savedProject = repo.save(project);
        Machine machine = new Machine(savedProject.getId(), 0, 0, 0, 0);
        machineRepository.save(machine);
        Material material = new Material(savedProject.getId(), 0, 0, 0,0);
        materialRepository.save(material);
        return savedProject;
    }



    /**
     * request for filtering fits this category more
     */
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

        if (roomType != null) {
            returnList = projectServiceLayer.findByRoomType(roomType);
        }

        if (name != null && roomType != null) {
            returnList = projectServiceLayer.findByRoomTypeAndName(roomType, name);
        }

        return returnList;
    }

    @PutMapping("/api/projects")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void putProject(@RequestBody Project project) throws Exception {
        repo.save(project);
    }


    @GetMapping("/api/projects/id/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ProjectViewModel getProjectsById(@PathVariable Integer id) {

        ProjectViewModel pvm = projectServiceLayer.findById(id);

        return pvm;
    }

//    @GetMapping("/api/projects/deadline/{deadline}")
//    @ResponseStatus(value = HttpStatus.OK)
//    public List<ProjectViewModel> findByDeadline(@PathVariable LocalDate deadline){
//        return projectServiceLayer.findByDeadline(deadline);
//    }

//    @GetMapping("/api/projects/startDate/{startDate}")
//    @ResponseStatus(value = HttpStatus.OK)
//    public List<ProjectViewModel> findByStartDate(@PathVariable LocalDate startDate) {
//        return projectServiceLayer.findByStartDate(startDate);
//    }

    @GetMapping("/api/projects/status/{status}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ProjectViewModel> findByStatus(@PathVariable String status) {
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
        List<ProjectViewModel> returnProjectList = projectServiceLayer.findByRoomTypeAndName(roomType, name);

        return returnProjectList;
    }


    @DeleteMapping("/api/projects/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable Integer id){
        machineRepository.deleteMachineByProjectId(id);
        materialRepository.deleteMaterialByProjectId(id);
        projectServiceLayer.deleteProject(id);
    }

}
