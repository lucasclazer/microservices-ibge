package com.lucas.ibgereport.controllers;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.lucas.ibgereport.dtos.ibge.ReportDTO;
import com.lucas.ibgereport.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        ServletOutputStream servletOutputStream = response.getOutputStream();
        this.cityService.getCityByRegionCSV(abbreviation).writeTo(servletOutputStream);

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=cities.csv");
        response.flushBuffer();
        return servletOutputStream;
    }

}
