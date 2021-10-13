package com.company.constructionmanagementsystem.controller;


import com.company.constructionmanagementsystem.exceptions.NotFoundException;
import com.company.constructionmanagementsystem.model.Material;
import com.company.constructionmanagementsystem.repository.MachineRepository;
import com.company.constructionmanagementsystem.repository.MaterialRepository;
import com.company.constructionmanagementsystem.util.feign.MaterialWarehouseClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
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
    @PostMapping("/api/requestMaterials")
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
    public Material getProjectMaterial(@PathVariable Integer projectId){
        Optional<Material> returnVal = repo.findByProjectId(projectId);
        if(!returnVal.isPresent()){
            throw new NotFoundException("This project has no materials");
        } else{
            return returnVal.get();
        }
    }

    /** get warehouse inventory*/
    @GetMapping("/api/material")
    public Material getWarehouseInventory() {
        return materialWarehouseClient.getWarehouseInventory();
    }

    /** refill warehouse inventory */
    @PutMapping("/api/material/refill")
    public String updateMaterialRefill() {
        return materialWarehouseClient.updateMaterialRefill();
    }
}
