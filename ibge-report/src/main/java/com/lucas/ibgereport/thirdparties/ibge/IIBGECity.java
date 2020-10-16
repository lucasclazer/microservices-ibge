package com.lucas.ibgereport.thirdparties.ibge;

import com.lucas.ibgereport.dtos.ibge.CityDto;
import com.lucas.ibgereport.thirdparties.configurations.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Collection;

@FeignClient(name = "IIBGECity", url = "https://servicodados.ibge.gov.br", configuration = FeignConfiguration.class)
public interface IIBGECity {
    @GetMapping(value = "api/v1/localidades/estados/{UF}/municipios")
    public abstract Collection<CityDto> getByRegion(@PathVariable(value = "UF", required = true) String abbreviation);
}
