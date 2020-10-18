package com.lucas.ibgereport.dtos.ibge;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lucas.ibgereport.dtos.deserializers.IBGECityDeserializer;
import com.lucas.ibgereport.dtos.deserializers.IBGERegionDeserializer;
import lombok.Data;

@Data
@JsonDeserialize( using = IBGERegionDeserializer.class )
public class RegionDTO {
    private long id;
    private String abbreviation;
    private String name;

    public RegionDTO(long id, String abbreviation, String name) {
        this.id = id;
        this.abbreviation = abbreviation;
        this.name = name;
    }

    public RegionDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
