package com.lucas.ibgereport.services;

import com.lucas.ibgereport.thirdparties.ibge.IIBGECity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    private final IIBGECity iibgeCity;

    @Autowired
    public CityService(IIBGECity iibgeCity) {
        this.iibgeCity = iibgeCity;
    }
}
