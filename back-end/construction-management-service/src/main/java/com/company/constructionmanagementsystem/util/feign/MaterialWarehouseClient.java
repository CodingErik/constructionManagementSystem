package com.company.constructionmanagementsystem.util.feign;


import com.company.constructionmanagementsystem.model.Material;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "material-warehouse-service")
public interface MaterialWarehouseClient {

    @GetMapping("/api/material")
    public Material getWarehouseInventory();

    @PutMapping("/api/material")
    public void updateMaterialAfterRetrieve(@RequestBody Material material);

    @PutMapping("/api/material/refill")
    public String updateMaterialRefill();
}