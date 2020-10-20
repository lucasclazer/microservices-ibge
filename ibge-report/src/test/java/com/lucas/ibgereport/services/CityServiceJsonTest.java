package com.lucas.ibgereport.services;

import com.lucas.ibgereport.IbgeReportApplication;
import com.lucas.ibgereport.dtos.ibge.ReportDTO;
import com.lucas.ibgereport.dtos.ibge.city.CityFeignDTO;
import com.lucas.ibgereport.services.city.CityServiceJson;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IbgeReportApplication.class)
@WebAppConfiguration
public class CityServiceJsonTest {

    @Autowired
    private CityServiceJson cityServiceJson;

    @Test
    public void getCityByRegion() throws Exception {
        Collection<ReportDTO> cities = this.cityServiceJson.getCityByRegion("sc|pr");
        assertThat(cities).isNotNull();
        assertThat(cities.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    public void getCityIdByName() throws Exception {
        long cityId = this.cityServiceJson.getCityIdByName("Brasília");
        assertThat(cityId).isNotNull();
        assertThat(cityId).isGreaterThanOrEqualTo(0);
    }

    @Test
    public void getCityIdByNameNotFound() throws Exception {
        long cityId = this.cityServiceJson.getCityIdByName("not-Brasília");
        assertThat(cityId).isNotNull();
        assertThat(cityId).isGreaterThanOrEqualTo(0);
    }

    @Test
    public void getAllCitiesFromBrazil() throws Exception {
        Collection<CityFeignDTO> cities = this.cityServiceJson.getAllCitiesFromBrazil();
        assertThat(cities).isNotNull();
        assertThat(cities.size()).isGreaterThanOrEqualTo(0);
    }

}
