package com.company.constructionmanagementsystem.controller;


import com.company.constructionmanagementsystem.exceptions.NotFoundException;
import com.company.constructionmanagementsystem.model.Material;
import com.company.constructionmanagementsystem.repository.MachineRepository;
import com.company.constructionmanagementsystem.repository.MaterialRepository;
import com.company.constructionmanagementsystem.service.MaterialServiceLayer;
import com.company.constructionmanagementsystem.util.feign.MaterialWarehouseClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin
@RefreshScope
public class MaterialController {

    @Autowired
    MaterialRepository repo;

    @Autowired
    MaterialWarehouseClient materialWarehouseClient;

    @Autowired
    MaterialServiceLayer materialServiceLayer;

    /**
     * update project specific inventory
     * update warehouse inventory
     * */
//    @PostMapping("/api/materials/project/request")
//    @ResponseStatus(HttpStatus.CREATED)
//    public String requestMaterials(@RequestBody Material materialForWarehouse, @RequestBody Material finalProjectMaterials) {
//        /** adding stock to main service
//         * calling the microservice to update the warehouse inventory
//         * */
//
//        try {
//            materialWarehouseClient.updateMaterialAfterRetrieve(materialForWarehouse);
//            repo.save(finalProjectMaterials);
//            return "the following material was added to the project " + materialForWarehouse.toString();
//        } catch (FeignException e) {
//            System.out.println(e.getMessage());
//            return e.getMessage();
//        }
//    }

    @PostMapping("/api/materials/project/request")
    @ResponseStatus(HttpStatus.CREATED)
    public String requestMaterials(@RequestBody Material requestMaterials) {
        /** adding stock to main service
         * calling the microservice to update the warehouse inventory
         * */

        return materialServiceLayer.requestMaterials(requestMaterials);
//        try {
//            materialWarehouseClient.updateMaterialAfterRetrieve(requestMaterials);
//            Material currentProjectMaterials = repo.findByProjectId(requestMaterials.getProjectId()).get();
//            Material finalProjectMaterials = new Material();
//            finalProjectMaterials.setBrick(currentProjectMaterials.getBrick() + requestMaterials.getBrick());
//            finalProjectMaterials.setCement(currentProjectMaterials.getCement() + requestMaterials.getCement());
//            finalProjectMaterials.setLumber(currentProjectMaterials.getLumber() + requestMaterials.getLumber());
//            finalProjectMaterials.setSteel(currentProjectMaterials.getSteel() + requestMaterials.getSteel());
//            finalProjectMaterials.setProjectId(requestMaterials.getProjectId());
//            finalProjectMaterials.setId(requestMaterials.getId());
//
//            repo.save(finalProjectMaterials);
//            return "the following material was added to the project " + requestMaterials.toString();
//        } catch (FeignException e) {
//            System.out.println(e.getMessage());
//            return e.getMessage();
//        }
    }

    /** get project specific inventory y*/
    @GetMapping("/api/materials/project/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public Material getProjectMaterial(@PathVariable Integer projectId){
        Material returnVal = repo.findByProjectId(projectId).get();
        if(returnVal == null){
            throw new NotFoundException("This project has no materials");
        }
        return returnVal;
    }

    @GetMapping("/api/materials")
    @ResponseStatus(HttpStatus.OK)
    public List<Material> getAllMaterialsInProjects() {
        List<Material> returnVal = repo.findAll();
        return returnVal;
    }

    /** get warehouse inventory*/
    @GetMapping("/api/materials/warehouse")
    @ResponseStatus(HttpStatus.OK)
    public Material getWarehouseInventory() {
        return materialWarehouseClient.getWarehouseInventory();
    }

    /** refill warehouse inventory */
    @PutMapping("/api/material/warehouse/refill")
    @ResponseStatus(HttpStatus.OK)
    public String updateMaterialRefill() {
        return materialWarehouseClient.updateMaterialRefill();
    }
}
