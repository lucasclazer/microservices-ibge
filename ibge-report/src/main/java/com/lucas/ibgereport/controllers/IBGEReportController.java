package com.lucas.ibgereport.controllers;

import com.lucas.ibgereport.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IBGEReportController {
    private CityService cityService;

    @Autowired

    public IBGEReportController(CityService cityService) {
        this.cityService = cityService;
    }


}
