package com.lucas.ibgereport.thirdparties.services;

import com.lucas.ibgereport.dtos.ibge.RegionDTO;
import com.lucas.ibgereport.thirdparties.services.feigninterfaces.IIBGERegion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class IBGERegionService {
    private final IIBGERegion iibgeRegion;
    private final ReactiveCircuitBreaker circuitBreaker;

    @Autowired
    public IBGERegionService(IIBGERegion iibgeRegion, ReactiveCircuitBreaker circuitBreaker) {
        this.iibgeRegion = iibgeRegion;
        this.circuitBreaker = circuitBreaker;
    }

    public Collection<RegionDTO> getAllRegions() throws Exception {
        var result = this.circuitBreaker.run(iibgeRegion.getAllRegions()).onErrorMap(throwable -> fallBackGetAllRegions(throwable));
        var regions = result.blockOptional();
        if(regions.isPresent())
            return regions.get();
        else
            throw new Exception("Exceeded limit of microservices requisitions!");
    }

    public Throwable fallBackGetAllRegions(Throwable throwable){
        throwable.addSuppressed(new Exception("Exceeded limit of microservices requisitions!"));
        return throwable;
    }
}
