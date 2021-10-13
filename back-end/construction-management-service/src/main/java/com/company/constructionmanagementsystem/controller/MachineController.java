package com.company.constructionmanagementsystem.controller;

import com.company.constructionmanagementsystem.exceptions.NotFoundException;
import com.company.constructionmanagementsystem.model.Machine;
import com.company.constructionmanagementsystem.model.Material;
import com.company.constructionmanagementsystem.repository.MachineRepository;
import com.company.constructionmanagementsystem.repository.MaterialRepository;
import com.company.constructionmanagementsystem.util.feign.MachineWarehouseClient;
import com.company.constructionmanagementsystem.util.feign.MaterialWarehouseClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin
@RefreshScope
public class MachineController {

    @Autowired
    MachineRepository repo;

    @Autowired
    MachineWarehouseClient machineWarehouseClient;



    /** get project specific inventory y*/
    @GetMapping("/api/machine/{projectId}")
    public Machine getProjectMachinery(@PathVariable Integer projectId){
        Optional<Machine> returnVal = repo.findByProjectId(projectId);
        if(!returnVal.isPresent()){
            throw new NotFoundException("This project has no machinery");
        } else{
            return returnVal.get();
        }
    }


    /** get the warehouse Inventory */
    @GetMapping("/api/machineryInventory")
    public Machine getMachineWarehouseInventory() {
        return machineWarehouseClient.getMachineryInventory();
    }

    /** to rent out, and update the warehouse inventoty*/
    @PostMapping("/api/requestMachinery")
    public String requestMachinery(@RequestBody Machine machine) {
        /** adding stock to main service
         * calling the microservice to update the warehouse inventory
         * */

        try {
            machineWarehouseClient.rentMachinery(machine);
            repo.save(machine);
            return "the following material was added to the project " + machine.toString();
        } catch (FeignException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }

    }

    /** to return machines to Machinery warehouse inventoty*/
    @PostMapping("/api/returnMachinery")
    public String returnMachinery(@RequestBody Machine machine) {

        try {
            machineWarehouseClient.returnMachinery(machine);
            return "thank you for using the machine microservice. ";
        } catch (FeignException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }


}


//    @PutMapping("/rentMachinery")
//    public void rentMachinery(@RequestBody Machine machinery) throws Exception {
//        machineWarehouseClient.rentMachinery(machinery);
//    }