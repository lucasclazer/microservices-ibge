package com.lucas.ibgereport.thirdparties.ibge;

import com.lucas.ibgereport.dtos.ibge.RegionDTO;
import com.lucas.ibgereport.thirdparties.configurations.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@FeignClient(name = "IIBGERegion", url = "https://servicodados.ibge.gov.br", configuration = FeignConfiguration.class)
public interface IIBGERegion {
    @GetMapping(value = "api/v1/localidades/estados")
    public abstract Collection<RegionDTO> getAllRegions();
}
