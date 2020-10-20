package com.lucas.ibgereport.services;

import com.lucas.ibgereport.IbgeReportApplication;
import com.lucas.ibgereport.services.region.RegionService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IbgeReportApplication.class)
@WebAppConfiguration
public class RegionServiceTest {
    @Autowired
    private RegionService regionService;

    public void getAllRegions() throws Exception {
        var regions = this.regionService.getAllRegions();
        assertThat(regions).isNotNull();
        assertThat(regions.size()).isGreaterThan(0);
    }

    public void getAllRegionsEmpty() throws Exception {
        var regions = this.regionService.getAllRegions();
        assertThat(regions).isNotNull();
        assertThat(regions.size()).isGreaterThanOrEqualTo(0);
    }
}
