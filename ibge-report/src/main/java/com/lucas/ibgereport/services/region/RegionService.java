package com.lucas.ibgereport.services.region;

import com.lucas.ibgereport.dtos.ibge.RegionDTO;
import com.lucas.ibgereport.thirdparties.ibge.IIBGERegion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class RegionService {
    private final IIBGERegion iibgeRegion;

    @Autowired
    public RegionService(IIBGERegion iibgeRegion) {
        this.iibgeRegion = iibgeRegion;
    }

    public Collection<RegionDTO> getAllRegions(){
        return this.iibgeRegion.getAllRegions();
    }

}
