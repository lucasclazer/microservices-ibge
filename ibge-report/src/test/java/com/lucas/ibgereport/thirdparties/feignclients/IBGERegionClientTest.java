package com.lucas.ibgereport.thirdparties.feignclients;

import com.lucas.ibgereport.IbgeReportApplication;
import com.lucas.ibgereport.thirdparties.services.feigninterfaces.IIBGERegion;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IbgeReportApplication.class)
@WebAppConfiguration
public class IBGERegionClientTest {
    @Autowired
    private IIBGERegion iibgeRegion;
    //Need to fix
    public void getAllRegions(){
        var regions = this.iibgeRegion.getAllRegions();
        assertThat(regions).isNotNull();
        assertThat(regions.size()).isGreaterThan(0);
    }
}
