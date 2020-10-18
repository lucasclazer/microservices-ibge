package com.lucas.ibgereport.services.city;

import com.lucas.ibgereport.dtos.ibge.CityDTO;
import com.lucas.ibgereport.dtos.ibge.ReportDTO;
import com.lucas.ibgereport.services.city.interfaces.ICityService;
import com.lucas.ibgereport.thirdparties.services.IBGECityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CityServiceJson implements ICityService {
    private final IBGECityService ibgeCityService;

    @Autowired
    public CityServiceJson(IBGECityService iibgeCity, ReactiveCircuitBreaker circuitBreaker) {
        this.ibgeCityService = iibgeCity;
    }

    @Override
    public Collection<ReportDTO> getCityByRegion(String abbreviation) throws Exception {
        Collection<CityDTO> cities = ibgeCityService.getByRegion(abbreviation);
        return cities.stream().map( c -> new ReportDTO(
                c.getIdRegion(),
                c.getRegionAbbreviation(),
                c.getRegionName(),
                c.getName()
        )).collect(Collectors.toCollection(ArrayList<ReportDTO>::new));
    }

    @Cacheable(value="citiesPerName", key="#cityName")
    public long getCityIdByName(String cityName) throws Exception {
        var city = this.getAllCitiesFromBrazil()
                .stream()
                .filter((value) -> value.getName().toUpperCase().equals(cityName.toUpperCase()))
                .findFirst();
        if (city.isPresent())
            return city.get().getId();
        return 0L;
    }

    @Cacheable(value="cities", unless = "#result == null or #result.size() == 0")
    public Collection<CityDTO> getAllCitiesFromBrazil() throws Exception {
        return this.ibgeCityService.getCitiesFromBrazil();
    }



}
