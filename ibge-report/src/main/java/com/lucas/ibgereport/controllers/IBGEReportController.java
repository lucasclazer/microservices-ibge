package com.lucas.ibgereport.controllers;

import com.lucas.ibgereport.dtos.ibge.CityDTO;
import com.lucas.ibgereport.dtos.ibge.ReportDTO;
import com.lucas.ibgereport.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collection;


@RestController
public class IBGEReportController {
    private CityService cityService;

    @Autowired
    public IBGEReportController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping(path = "/cities/{abbreviation}")
    public Collection<ReportDTO> getCityByRegion(@PathVariable(value = "abbreviation") String abbreviation){
        return cityService.getCityByRegion(abbreviation);
    }

    @GetMapping(path = "/cities/csv/{abbreviation}")
    public OutputStream getCityByRegionCsv(HttpServletResponse response, @PathVariable(value = "abbreviation") String abbreviation) throws IOException {
        response.setHeader("Content-Disposition","attachment; filename=cities.csv");
        OutputStream servletOutputStream = response.getOutputStream();
        this.cityService.getCityByRegionCSV(abbreviation).writeTo(servletOutputStream);
        servletOutputStream.flush();
        servletOutputStream.close();
        return servletOutputStream;
    }

    @GetMapping(path = "/cities/brazil")
    public Collection<CityDTO> getCityByRegion(){
        return cityService.getAllCitiesFromBrazil();
    }

    @GetMapping(path = "/cityIdPerName/{name}")
    public long getIdPerName(@PathVariable(value = "name") String name){
        return cityService.getCityIdByName(name);
    }


}
