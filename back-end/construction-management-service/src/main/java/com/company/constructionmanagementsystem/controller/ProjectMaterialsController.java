package com.company.constructionmanagementsystem.controller;

import com.company.constructionmanagementsystem.model.ProjectMaterials;
import com.company.constructionmanagementsystem.repository.ProjectMaterialsRepository;
import com.company.constructionmanagementsystem.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ProjectMaterialsController {

    @Autowired
    ProjectMaterialsRepository repository;

    @Autowired
    ProjectRepository projectRepository;

    @PostMapping("/api/projectMaterials")
    public ProjectMaterials requestMaterialsForNewProject(@RequestBody ProjectMaterials projectMaterials) {
        ProjectMaterials materials = new ProjectMaterials();

        repository.save(materials);

        return materials;
    }

    @GetMapping("/api/projectMaterials/{projectId}")
    public ProjectMaterials getProjectMaterials(@PathVariable Integer projectId){
        ProjectMaterials materials = repository.findByProjectId(projectId).get();

        return materials;
    }

}
