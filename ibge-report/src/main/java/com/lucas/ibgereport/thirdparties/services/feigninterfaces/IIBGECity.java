package com.lucas.ibgereport.thirdparties.services.feigninterfaces;

import com.lucas.ibgereport.dtos.ibge.CityDTO;
import com.lucas.ibgereport.thirdparties.configurations.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;
import java.util.Collection;

@FeignClient(name = "IIBGECity", url = "https://servicodados.ibge.gov.br", configuration = FeignConfiguration.class)
public interface IIBGECity {
    @GetMapping(value = "api/v1/localidades/estados/{UF}/municipios")
    Mono<Collection<CityDTO>> getByRegion(@PathVariable(value = "UF", required = true) String abbreviation);

    @GetMapping(value = "api/v1/localidades/municipios")
    Mono<Collection<CityDTO>> getCitiesFromBrazil();
}
