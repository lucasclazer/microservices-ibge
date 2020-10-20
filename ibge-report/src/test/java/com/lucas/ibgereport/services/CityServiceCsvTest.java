package com.lucas.ibgereport.services;

import com.lucas.ibgereport.IbgeReportApplication;
import com.lucas.ibgereport.services.city.CityServiceCsv;
import com.lucas.ibgereport.thirdparties.services.feigninterfaces.IIBGECity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IbgeReportApplication.class)
@WebAppConfiguration
public class CityServiceCsvTest {

    @Autowired
    private CityServiceCsv cityServiceCsv;

    @Mock
    private IIBGECity iibgeCity;

    @Test
    public void getCityByRegionCsv() throws Exception {
        var csv = this.cityServiceCsv.getCityByRegion("sc");
        assertThat(csv).isNotNull();
        assertThat(csv.size()).isGreaterThan(0);
    }

    @Test
    public void getCityByRegionCsvEmpty() throws Exception {
        var csv = this.cityServiceCsv.getCityByRegion("");
        assertThat(csv).isNotNull();
        assertThat(csv.size()).isGreaterThanOrEqualTo(0);
    }



}
