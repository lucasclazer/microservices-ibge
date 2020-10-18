package com.lucas.ibgereport.services.region;

import com.lucas.ibgereport.dtos.ibge.RegionDTO;
import com.lucas.ibgereport.thirdparties.services.IBGERegionService;
import com.lucas.ibgereport.thirdparties.services.feigninterfaces.IIBGERegion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class RegionService {
    private final IBGERegionService ibgeRegionService;

    @Autowired
    public RegionService(IBGERegionService ibgeRegionService) {
        this.ibgeRegionService = ibgeRegionService;
    }

    public Collection<RegionDTO> getAllRegions() throws Exception {
        return this.ibgeRegionService.getAllRegions();
    }

}
