package com.company.constructionmanagementsystem.util.feign;


import com.company.constructionmanagementsystem.model.Machine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "machine-warehouse-service")
public interface MachineWarehouseClient {

    @GetMapping("/api/machinery")
    public Machine getMachineryInventory();

    @PutMapping("/api/machinery")
    public void rentMachinery(@RequestBody Machine machinery);

    @PutMapping("/api/machinery/return")
    public void returnMachinery(@RequestBody Machine machinery);

}

/*** way to import the model */
/** is is okay for them to just be objects */
/** models are shared in a library */