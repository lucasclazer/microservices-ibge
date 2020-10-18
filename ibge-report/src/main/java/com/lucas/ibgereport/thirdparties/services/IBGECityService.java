package com.lucas.ibgereport.thirdparties.services;

import com.lucas.ibgereport.thirdparties.services.feigninterfaces.IIBGECity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class IBGECityService {
    private final IIBGECity iibgeCity;
    private final ReactiveCircuitBreaker circuitBreaker;

    @Autowired
    public IBGECityService(IIBGECity iibgeCity, ReactiveCircuitBreaker circuitBreaker) {
        this.iibgeCity = iibgeCity;
        this.circuitBreaker = circuitBreaker;
    }

    public Collection getCitiesFromBrazil() throws Exception {
        var result =
                this.circuitBreaker.run(Mono
                .just(this.iibgeCity.getCitiesFromBrazil()), this::fallbackCitiesFromBrazil)
                .blockOptional();

        if(result.isPresent())
            return result.get();
        else
            throw new Exception("Error on getCitiesFromBrazil!");
    }
    public Collection getByRegion(String abbreviation) throws Exception {
        var result = this.circuitBreaker.run(Mono
                .just(this.iibgeCity.getByRegion(abbreviation)), this::fallbackCitiesFromBrazil)
                .blockOptional();

        if(result.isPresent())
            return result.get();
        else
            throw new Exception("Error on getByRegion!");
    }

    public Mono<Collection> fallbackCitiesFromBrazil(Throwable throwable){
        throwable.addSuppressed(new Exception("Exceeded limit of microservices requisitions!"));
        //Implement here alternative method for return better result for user / another service.
        return Mono.just(new ArrayList());
    }

}
