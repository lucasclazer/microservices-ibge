package com.lucas.ibgereport.controllers;


import com.lucas.ibgereport.controllers.base.AbstractControllerBaseTest;
import com.lucas.ibgereport.dtos.ibge.RegionDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import static org.junit.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class RegionControllerTest extends AbstractControllerBaseTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getAllRegions() throws Exception {
        var result = this.mvc.perform(get("/api/v1/regions/brazil")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        var regions = super.mapFromJson(result.getResponse().getContentAsString(), RegionDTO[].class);
        assertThat(regions).isNotNull();
        assertTrue(regions.length >= 0);

    }
}
