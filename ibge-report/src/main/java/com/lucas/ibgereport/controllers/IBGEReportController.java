package com.lucas.ibgereport.controllers;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.lucas.ibgereport.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class IBGEReportController {
    private CityService cityService;

    @Autowired

    public IBGEReportController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping(path = "/cities/csv/{abbreviation}", produces = "text/csv")
    public String getCityByRegionCsv(HttpServletResponse response, @PathVariable(value = "abbreviation") String abbreviation) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
        ServletOutputStream servletOutputStream = response.getOutputStream();
        this.cityService.getCityByRegionCSV(abbreviation).writeTo(servletOutputStream);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=citiesPerRegion.csv");
        response.flushBuffer();
        return "ok";
    }

}
