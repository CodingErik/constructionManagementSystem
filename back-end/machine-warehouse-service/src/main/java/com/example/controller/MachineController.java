package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class MachineController {

    @Value("${materialWarehouse}")
    private String materialWarehouse;

    @Value("${servicePath}")
    private String servicePath;

    @RequestMapping(value="/machine", method = RequestMethod.GET)
    public String helloCloud() {

        return materialWarehouse + servicePath;
    }

}
