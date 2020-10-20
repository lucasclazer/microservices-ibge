package com.lucas.ibgereport.thirdparties.services;

import com.lucas.ibgereport.IbgeReportApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IBGECityService.class)
@WebAppConfiguration
public class IBGECityServiceTest {
    @Autowired
    private IBGECityService ibgeCityService;

    public void getByRegion() throws Exception {
        var cities = this.ibgeCityService.getByRegion("pr");
        assertThat(cities).isNotNull();
        assertThat(cities.size()).isGreaterThan(0);
    }

    public void getCitiesFromBrazil() throws Exception {
        var cities = this.ibgeCityService.getCitiesFromBrazil();
        assertThat(cities).isNotNull();
        assertThat(cities.size()).isGreaterThan(0);
    }
}
