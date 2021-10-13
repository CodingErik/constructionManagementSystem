package com.company.constructionmanagementsystem.util.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "material-warehouse-service")
public interface MaterialWarehouseClient {
//
//    @GetMapping("/material")
//    public String helloCloud();
}