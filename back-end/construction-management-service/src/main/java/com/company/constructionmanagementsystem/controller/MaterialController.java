package com.company.constructionmanagementsystem.controller;


import com.company.constructionmanagementsystem.exceptions.NotFoundException;
import com.company.constructionmanagementsystem.model.Material;
import com.company.constructionmanagementsystem.repository.MachineRepository;
import com.company.constructionmanagementsystem.repository.MaterialRepository;
import com.company.constructionmanagementsystem.util.feign.MaterialWarehouseClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
//@CrossOrigin
@RefreshScope
public class MaterialController {

    @Autowired
    MaterialRepository repo;

    @Autowired
    MaterialWarehouseClient materialWarehouseClient;

    /**
     * update project specific inventory
     * update warehouse inventory
     * */
    @PostMapping("/api/materials/request")
    @ResponseStatus(HttpStatus.CREATED)
    public String requestMaterials(@RequestBody Material material) {
        /** adding stock to main service
         * calling the microservice to update the warehouse inventory
         * */

        try {
            materialWarehouseClient.updateMaterialAfterRetrieve(material);
            repo.save(material);
            return "the following material was added to the project " + material.toString();
        } catch (FeignException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    /** get project specific inventory y*/
    @GetMapping("/api/materials/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public Material getProjectMaterial(@PathVariable Integer projectId){
        Material returnVal = repo.findByProjectId(projectId).get();
        if(returnVal == null){
            throw new NotFoundException("This project has no materials");
        }
        return returnVal;
    }

    /** get warehouse inventory*/
    @GetMapping("/api/materials")
    @ResponseStatus(HttpStatus.OK)
    public Material getWarehouseInventory() {
        return materialWarehouseClient.getWarehouseInventory();
    }

    /** refill warehouse inventory */
    @PutMapping("/api/material/refill")
    @ResponseStatus(HttpStatus.OK)
    public String updateMaterialRefill() {
        return materialWarehouseClient.updateMaterialRefill();
    }
}
