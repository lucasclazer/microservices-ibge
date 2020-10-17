package com.lucas.ibgereport.services.city;

import com.lucas.ibgereport.dtos.ibge.CityDTO;
import com.lucas.ibgereport.dtos.ibge.ReportDTO;
import com.lucas.ibgereport.services.city.interfaces.ICityService;
import com.lucas.ibgereport.thirdparties.ibge.IIBGECity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CityServiceJson implements ICityService {
    private IIBGECity iibgeCity;

    @Autowired
    public CityServiceJson(IIBGECity iibgeCity) {
        this.iibgeCity = iibgeCity;
    }

    @Override
    public Collection<ReportDTO> getCityByRegion(String abbreviation) {
        return iibgeCity.getByRegion(abbreviation).stream().map( c -> new ReportDTO(
                c.getIdRegion(),
                c.getRegionAbbreviation(),
                c.getRegionName(),
                c.getName()
        )).collect(Collectors.toCollection(ArrayList<ReportDTO>::new));

    }

    @Cacheable(value="citiesPerName", key="#cityName")
    public long getCityIdByName(String cityName){
        Collection<CityDTO> cities = this.getAllCitiesFromBrazil();
        var city = cities.stream().filter((value) -> value.getName().toUpperCase().equals(cityName.toUpperCase())).findFirst();
        if (city.isPresent())
            return city.get().getId();

        return 0L;
    }

    @Cacheable(value="cities", unless = "#result == null or #result.size() == 0")
    public Collection<CityDTO> getAllCitiesFromBrazil(){
        return this.iibgeCity.getCitiesFromBrazil();
    }
}
