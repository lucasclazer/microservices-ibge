package com.lucas.ibgereport.controllers;

import com.lucas.ibgereport.dtos.ibge.CityDTO;
import com.lucas.ibgereport.dtos.ibge.ReportDTO;
import com.lucas.ibgereport.services.city.CityServiceCsv;
import com.lucas.ibgereport.services.city.CityServiceJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collection;


@RestController
@RequestMapping("/api/v1/cities")
public class ReportController {
    private CityServiceJson cityServiceJson;
    private CityServiceCsv cityServiceCsv;

    @Autowired
    public ReportController(CityServiceJson cityServiceJson, CityServiceCsv cityServiceCsv) {
        this.cityServiceJson = cityServiceJson;
        this.cityServiceCsv = cityServiceCsv;
    }

    @CrossOrigin
    @GetMapping(path = "/{abbreviation}")
    public Collection<ReportDTO> getCityByRegion(@PathVariable(value = "abbreviation") String abbreviation) throws Exception {
        return cityServiceJson.getCityByRegion(abbreviation);
    }

    @CrossOrigin
    @GetMapping(path = "/csv/{abbreviation}")
    public OutputStream getCityByRegionCsv(HttpServletResponse response, @PathVariable(value = "abbreviation") String abbreviation) throws Exception {
        response.setHeader("Content-Disposition","attachment; filename=cities.csv");
        OutputStream servletOutputStream = response.getOutputStream();
        cityServiceCsv.getCityByRegion(abbreviation).writeTo(servletOutputStream);
        servletOutputStream.flush();
        servletOutputStream.close();
        return servletOutputStream;
    }

    @CrossOrigin
    @GetMapping(path = "/brazil")
    public Collection<CityDTO> getCityByRegion() throws Exception {
        return cityServiceJson.getAllCitiesFromBrazil();
    }

    @CrossOrigin
    @GetMapping(path = "/cityIdPerName/{name}")
    public long getIdPerName(@PathVariable(value = "name") String name) throws Exception {
        return cityServiceJson.getCityIdByName(name);
    }


}
