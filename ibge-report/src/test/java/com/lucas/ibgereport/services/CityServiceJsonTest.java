package com.lucas.ibgereport.services;

import com.lucas.ibgereport.IbgeReportApplication;
import com.lucas.ibgereport.services.city.CityServiceJson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IbgeReportApplication.class)
@WebAppConfiguration
public class CityServiceJsonTest {

    private CityServiceJson cityServiceJson;

    @Autowired
    public CityServiceJsonTest(CityServiceJson cityServiceJson) {
        this.cityServiceJson = cityServiceJson;
    }

    @Test
    public void getCityByRegionCsv() throws Exception {


    }

}
