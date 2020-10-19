package com.lucas.ibgereport.dtos.ibge.city;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lucas.ibgereport.dtos.deserializers.IBGECityDeserializer;
import lombok.Data;


public class CityDTO {
    private long id;
    private String name;
    private long idRegion;
    private String regionAbbreviation;
    private String regionName;

    public CityDTO() {
    }

    public CityDTO(long id, String name, long idRegion, String regionAbbreviation, String regionName) {
        this.id = id;
        this.name = name;
        this.idRegion = idRegion;
        this.regionAbbreviation = regionAbbreviation;
        this.regionName = regionName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(long idRegion) {
        this.idRegion = idRegion;
    }

    public String getRegionAbbreviation() {
        return regionAbbreviation;
    }

    public void setRegionAbbreviation(String regionAbbreviation) {
        this.regionAbbreviation = regionAbbreviation;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
