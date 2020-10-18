package com.lucas.ibgereport.controllers;

import com.lucas.ibgereport.dtos.ibge.RegionDTO;
import com.lucas.ibgereport.services.region.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class RegionController {
    private RegionService regionService;

    @Autowired
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping(path = "regions/brazil")
    public Collection<RegionDTO> getAllRegionsFromBrazil() throws Exception {
        return this.regionService.getAllRegions();
    }
}
