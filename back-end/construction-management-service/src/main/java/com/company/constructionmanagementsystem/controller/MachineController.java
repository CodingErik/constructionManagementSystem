package com.company.constructionmanagementsystem.controller;

import com.company.constructionmanagementsystem.exceptions.NotFoundException;
import com.company.constructionmanagementsystem.model.Machine;
import com.company.constructionmanagementsystem.model.Material;
import com.company.constructionmanagementsystem.repository.MachineRepository;
import com.company.constructionmanagementsystem.repository.MaterialRepository;
import com.company.constructionmanagementsystem.service.MachineServiceLayer;
import com.company.constructionmanagementsystem.util.feign.MachineWarehouseClient;
import com.company.constructionmanagementsystem.util.feign.MaterialWarehouseClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
//@RefreshScope
public class MachineController {

    @Autowired
    MachineRepository repo;

    @Autowired
    MachineWarehouseClient machineWarehouseClient;

    @Autowired
    MachineServiceLayer machineServiceLayer;

    /** get project specific inventory y*/
    @GetMapping("/api/machines/project/{projectId}")
    public Machine getProjectMachinery(@PathVariable Integer projectId){
        Optional<Machine> returnVal = repo.findByProjectId(projectId);
        if(!returnVal.isPresent()){
            throw new NotFoundException("This project has no machinery");
        } else{
            return returnVal.get();
        }
    }


    /** get the warehouse Inventory */
    @GetMapping("/api/machines/warehouse")
    @ResponseStatus(HttpStatus.OK)
    public Machine getMachineWarehouseInventory() {
        return machineWarehouseClient.getMachineryInventory();
    }

    /** test needed*/
    @GetMapping("/api/machines")
    @ResponseStatus(HttpStatus.OK)
    public List<Machine> getAllMachinesInProjects() {
        List<Machine> returnVal = repo.findAll();
        return returnVal;
    }

    /** to rent out, and update the warehouse inventoty*/
    @PostMapping("/api/machines/project/request")
    @ResponseStatus(HttpStatus.CREATED) /** was no content*/
    public String requestMachinery(@RequestBody Machine machine) {
        /** adding stock to main service
         * calling the microservice to update the warehouse inventory
         * */

        return machineServiceLayer.requestMachinery(machine);

    }


    /** to return machines to Machinery warehouse inventoty*/
    @PostMapping("/api/machines/project/return")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String returnMachinery(@RequestBody Machine machine) {

        try {
            machineWarehouseClient.returnMachinery(machine);
//            repo.deleteAll();
            Machine oldMachine = repo.getById(machine.getId());
            Machine updatedMachine = new Machine();
            updatedMachine.setId(machine.getId());
            updatedMachine.setProjectId(machine.getProjectId());
            updatedMachine.setCrane(oldMachine.getCrane()-machine.getCrane());
            updatedMachine.setDrill(oldMachine.getDrill()-machine.getDrill());
            updatedMachine.setForklift(oldMachine.getForklift()-machine.getForklift());
            updatedMachine.setLadder(oldMachine.getLadder()-machine.getLadder());
            repo.save(updatedMachine);
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