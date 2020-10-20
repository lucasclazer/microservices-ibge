package com.lucas.ibgereport.thirdparties.feignclients;

import com.lucas.ibgereport.IbgeReportApplication;
import com.lucas.ibgereport.thirdparties.services.feigninterfaces.IIBGECity;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IbgeReportApplication.class)
@WebAppConfiguration
public class IBGECityClientTest {
    @Autowired
    private IIBGECity iibgeCity;
    //Need to fix
    public void getByRegion(){
        var cities = this.iibgeCity.getByRegion("pr");
        assertThat(cities).isNotNull();
        assertThat(cities.size()).isGreaterThan(0);
    }

    public void getCitiesFromBrazil(){
        var cities = this.iibgeCity.getCitiesFromBrazil();
        assertThat(cities).isNotNull();
        assertThat(cities.size()).isGreaterThan(0);
    }
}
