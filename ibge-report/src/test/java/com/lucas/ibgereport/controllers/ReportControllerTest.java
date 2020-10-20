package com.lucas.ibgereport.controllers;

import com.lucas.ibgereport.controllers.base.AbstractControllerBaseTest;
import com.lucas.ibgereport.dtos.ibge.ReportDTO;
import com.lucas.ibgereport.dtos.ibge.city.CityDTO;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class ReportControllerTest extends AbstractControllerBaseTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getCityByRegion() throws Exception {
        var result = this.testRequisition("/api/v1/cities/sc");
        var cities = super.mapFromJson(result.getResponse().getContentAsString(), ReportDTO[].class);
        assertThat(cities).isNotNull();
        assertTrue(cities.length >= 0);
    }

    @Test
    public void getCityByRegionCsv() throws Exception {
        var result = this.testOutputStreamRequisition("/api/v1/cities/csv/sc");
        var outputStream = result.getResponse().getOutputStream();
        assertThat(outputStream).isNotNull();
    }

    @Test
    public void getCitiesFromCountry() throws Exception {
        var result = this.testRequisition("/api/v1/cities/country/brazil");
        var cities = super.mapFromJson(result.getResponse().getContentAsString(), CityDTO[].class);
        assertThat(cities).isNotNull();
        assertTrue(cities.length >= 0);
    }

}
