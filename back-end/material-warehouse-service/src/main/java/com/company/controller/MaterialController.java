package com.company.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class MaterialController {

    @Value("${materialWarehouse}")
    private String materialWarehouse;

    @Value("${servicePath}")
    private String servicePath;

    @RequestMapping(value="/material", method = RequestMethod.GET)
    public String helloCloud() {

        return materialWarehouse + servicePath;
    }
}



