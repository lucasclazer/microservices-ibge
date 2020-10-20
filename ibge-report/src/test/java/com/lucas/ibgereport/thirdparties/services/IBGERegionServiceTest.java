package com.lucas.ibgereport.thirdparties.services;

import com.lucas.ibgereport.IbgeReportApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IbgeReportApplication.class)
@WebAppConfiguration
public class IBGERegionServiceTest {
    @Autowired
    private IBGERegionService ibgeRegionService;

    public void getAllRegions() throws Exception {
        var regions = this.ibgeRegionService.getAllRegions();
        assertThat(regions).isNotNull();
        assertThat(regions.size()).isGreaterThan(0);
    }
}
