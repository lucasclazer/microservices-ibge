package com.lucas.ibgereport.thirdparties.services;

import com.lucas.ibgereport.dtos.ibge.RegionDTO;
import com.lucas.ibgereport.thirdparties.services.feigninterfaces.IIBGERegion;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.time.Duration;
import java.util.ArrayList;
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
    @CircuitBreaker(name="default", fallbackMethod = "fallBackGetAllRegions")
    public Collection<RegionDTO> getAllRegions() throws Exception {
        //That Method result comes with empty array because exceeds the duration.
        var result = this.circuitBreaker.run(Mono
                .just(iibgeRegion.getAllRegions())
                .delayElement(Duration.ofSeconds(15)), this::fallBackGetAllRegions);

        var regions = result.blockOptional();
        if(regions.isPresent())
            return regions.get();
        else
            throw new Exception("Exceeded limit of microservices requisitions!");
    }

    public Mono<Collection<RegionDTO>> fallBackGetAllRegions(Throwable throwable) {
        //Implement here alternative method for return better result for user / another service.
        throwable.addSuppressed(new Exception("Exceeded the number of requisitions!"));
        return Mono.just(new ArrayList<RegionDTO>());
    }
}
