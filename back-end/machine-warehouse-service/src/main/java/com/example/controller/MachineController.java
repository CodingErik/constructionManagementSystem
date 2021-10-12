package com.example.controller;

import com.example.model.Machinery;
import com.example.repositoty.MachineryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RefreshScope
public class MachineController {

    @Autowired
    MachineryRepository repo;

    @Value("${materialWarehouse}")
    private String materialWarehouse;

    @Value("${servicePath}")
    private String servicePath;

    @RequestMapping(value = "/machine", method = RequestMethod.GET)
    public String helloCloud() {

        return materialWarehouse + servicePath;
    }

    /**
     * get machinery inventory
     */
    @GetMapping("/api/machinery")
    public Machinery getMachineryInventory() {
        return repo.getById(1);
    }

    /**
     * retrieve stock needed for project
     */
    @PutMapping("/api/machinery")
    public void updateMaterialAfterRetrieve(@RequestBody Machinery machinery) {


        Machinery inventory = repo.getById(1);

        if (inventory.getCrane() - (machinery.getCrane()) < 0 || (inventory.getDrill() - machinery.getDrill()) < 0 ||
                (inventory.getForklift() - machinery.getForklift()) < 0 || (inventory.getLadder() - machinery.getLadder()) < 0) {

            String errorMsg = "this order doesn't have enough: ";

            /** refactor into object later*/
            if (inventory.getCrane() - (machinery.getCrane()) < 0) {
                errorMsg += "Cranes " + inventory.getCrane();
            }
            if (inventory.getDrill() - (machinery.getDrill()) < 0) {
                errorMsg += "Drills " + inventory.getDrill();
            }
            if (inventory.getForklift() - (machinery.getForklift()) < 0) {
                errorMsg += "Forklifts " + inventory.getForklift();
            }
            if (inventory.getLadder() - (machinery.getLadder()) < 0) {
                errorMsg += "Ladders " + inventory.getLadder();
            }

            throw new IllegalArgumentException(errorMsg);
        }

        inventory.setCrane(inventory.getCrane() - (machinery.getCrane()));
        inventory.setDrill(inventory.getDrill() - (machinery.getDrill()));
        inventory.setForklift(inventory.getForklift() - (machinery.getForklift()));
        inventory.setLadder(inventory.getLadder() - (machinery.getLadder()));


        repo.save(inventory);


    }

    /** when the project is done
     *  this route should be requested so that we can return all machinery
     *
     * */
    @PutMapping("/api/machinery/return")
    public String updateReturnMachinery(@RequestBody Machinery machinery) {

        Machinery inventory = repo.getById(1);

        /** limit is only 30 */
        int totalCrane = inventory.getCrane() + (machinery.getCrane());
        int totalDrill = inventory.getDrill() + (machinery.getDrill());
        int totalForklift = inventory.getForklift() + (machinery.getForklift());
        int totalLadder = inventory.getLadder() + (machinery.getLadder());

        if (totalCrane > 30) {
            inventory.setCrane(30);
        } else {
            inventory.setCrane(totalCrane);
        }
        if (totalDrill > 30) {
            inventory.setDrill(30);
        } else {
            inventory.setDrill(totalDrill);
        }
        if (totalForklift > 30) {
            inventory.setForklift(30);
        } else {
            inventory.setForklift(totalForklift);
        }
        if (totalLadder > 30) {
            inventory.setLadder(30);
        } else {
            inventory.setLadder(totalLadder);
        }

        repo.save(inventory);

        return "stock has been returned";
    }

}



//
//
//if (inventory.getCrane() - (machinery.getCrane()) < 0 || (inventory.getDrill() - machinery.getDrill()) < 0 ||
//        (inventory.getForklift() - machinery.getForklift()) < 0 || (inventory.getLadder() - machinery.getLadder()) < 0) {
//
//
//        /** refactor into object later*/
//        if (inventory.getCrane() + (machinery.getCrane()) < 0) {
//
//        }
//        if (inventory.getDrill() + (machinery.getDrill()) < 0) {
//
//        }
//        if (inventory.getForklift() + (machinery.getForklift()) < 0) {
//
//        }
//        if (inventory.getLadder() + (machinery.getLadder()) < 0) {
//
//        }
//
//
//        }