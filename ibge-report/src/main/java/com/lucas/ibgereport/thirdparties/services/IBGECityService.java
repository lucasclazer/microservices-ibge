package com.lucas.ibgereport.thirdparties.services;

import com.lucas.ibgereport.dtos.ibge.CityDTO;
import com.lucas.ibgereport.thirdparties.services.feigninterfaces.IIBGECity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class IBGECityService {
    private IIBGECity iibgeCity;
    private ReactiveCircuitBreaker circuitBreaker;

    @Autowired
    public IBGECityService(IIBGECity iibgeCity, ReactiveCircuitBreaker circuitBreaker) {
        this.iibgeCity = iibgeCity;
        this.circuitBreaker = circuitBreaker;
    }

    public Collection<CityDTO> getCitiesFromBrazil() throws Exception {
        var result =
                this.circuitBreaker.run( this.iibgeCity.getCitiesFromBrazil())
                .onErrorMap(throwable -> fallbackCitiesFromBrazil(throwable))
                .blockOptional();

        if(result.isPresent())
            return result.get();
        else
            throw new Exception("Error on getCitiesFromBrazil!");
    }
    public Collection<CityDTO> getByRegion(String abbreviation) throws Exception {
        var result = this.circuitBreaker.run(this.iibgeCity.getByRegion(abbreviation))
                .onErrorMap(throwable -> fallbackCitiesFromBrazil(throwable))
                .blockOptional();

        if(result.isPresent())
            return result.get();
        else
            throw new Exception("Error on getByRegion!");
    }

    public Throwable fallbackCitiesFromBrazil(Throwable throwable){
        throwable.addSuppressed(new Exception("Exceeded limit of microservices requisitions!"));
        return  throwable;
    }
}
