package com.company.constructionmanagementsystem.service;

import com.company.constructionmanagementsystem.model.Machine;
import com.company.constructionmanagementsystem.repository.MachineRepository;
import com.company.constructionmanagementsystem.util.feign.MachineWarehouseClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class MachineServiceLayer {

    MachineRepository machineRepository;
    MachineWarehouseClient machineWarehouseClient;

    @Autowired
    public MachineServiceLayer(MachineRepository machineRepository, MachineWarehouseClient machineWarehouseClient) {
        this.machineRepository = machineRepository;
        this.machineWarehouseClient = machineWarehouseClient;
    }

    public String requestMachinery(Machine machine) {

        try {
            machineWarehouseClient.rentMachinery(machine);

            Machine currentProjectMachines = machineRepository.findByProjectId(machine.getProjectId()).get();
            Machine updatedProjectMachines = new Machine();
            updatedProjectMachines.setProjectId(machine.getProjectId());
            updatedProjectMachines.setId(machine.getId());
            updatedProjectMachines.setCrane(currentProjectMachines.getCrane() + machine.getCrane());
            updatedProjectMachines.setDrill(currentProjectMachines.getDrill() + machine.getDrill());
            updatedProjectMachines.setForklift(currentProjectMachines.getForklift() + machine.getForklift());
            updatedProjectMachines.setLadder(currentProjectMachines.getLadder() + machine.getLadder());
            machineRepository.save(updatedProjectMachines);
            return "the following material was added to the project " + machine;
        } catch (FeignException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }

    }
}
