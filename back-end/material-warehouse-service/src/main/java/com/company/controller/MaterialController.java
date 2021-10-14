package com.company.controller;

import com.company.model.Material;
import com.company.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.NestedServletException;

import javax.ws.rs.HttpMethod;
import java.math.BigDecimal;

@RestController
@RefreshScope
public class MaterialController {

    @Autowired
    MaterialRepository repo;



    @Value("${materialWarehouse}")
    private String materialWarehouse;

    @Value("${servicePath}")
    private String servicePath;

    @RequestMapping(value = "/material", method = RequestMethod.GET)
    public String helloCloud() {

        return materialWarehouse + servicePath;
    }

    /**
     * current amount in warehouse stock
     */
    @GetMapping("/api/material")
    @ResponseStatus(HttpStatus.OK)
    public Material getWarehouseInventory() {
        return repo.getById(1);
    }

    /**
     * retrieve stock needed for project
     */
    @PutMapping("/api/material")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMaterialAfterRetrieve(@RequestBody Material material) {

        Material inventory = repo.getById(1);

        if (inventory.getBrick() - (material.getBrick()) < 0 || (inventory.getCement() - material.getCement()) < 0 ||
                (inventory.getSteel() - material.getSteel()) < 0 || (inventory.getLumber() - material.getLumber()) < 0) {

                String errorMsg = "this order doesn't have enough: ";

                /** refactor into object later*/
                if(inventory.getBrick() - (material.getBrick()) < 0 ){
                    errorMsg += "not enough brick. current stock is:" + inventory.getBrick() + "\n" ;
                }
                if(inventory.getCement() - (material.getCement()) < 0 ){
                    errorMsg += "not enough cement."+ inventory.getCement() + "\n" ;
                }
                if(inventory.getLumber() - (material.getLumber()) < 0 ){
                    errorMsg += "not enough lumber."+ inventory.getLumber() + "\n" ;
                }
                if(inventory.getSteel() - (material.getSteel()) < 0 ){
                    errorMsg += "not enough steel."+ inventory.getSteel() + "\n" ;
                }

                throw new IllegalArgumentException(errorMsg);
        }

        inventory.setBrick(inventory.getBrick() - (material.getBrick()));
        inventory.setSteel(inventory.getSteel() - (material.getSteel()));
        inventory.setCement(inventory.getCement() - (material.getCement()));
        inventory.setLumber(inventory.getLumber() - (material.getLumber()));


        repo.save(inventory);

    }


    @PutMapping("/api/material/refill")
    @ResponseStatus(HttpStatus.OK)
    public String updateMaterialRefill() {

        Material inventory = repo.getById(1);

        inventory.setBrick(1000);
        inventory.setSteel(1000);
        inventory.setCement(1000);
        inventory.setLumber(1000);

        repo.save(inventory);

        return "stock has been refilled";
    }



}



