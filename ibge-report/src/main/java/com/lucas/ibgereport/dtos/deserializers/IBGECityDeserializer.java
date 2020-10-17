package com.lucas.ibgereport.dtos.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.lucas.ibgereport.dtos.ibge.CityDTO;

import java.io.IOException;

public class IBGECityDeserializer extends StdDeserializer<CityDTO>{

    protected IBGECityDeserializer() {
        super((JavaType) null);
    }

    protected IBGECityDeserializer(Class<?> vc) {
        super(vc);
    }

    protected IBGECityDeserializer(JavaType valueType) {
        super(valueType);
    }

    protected IBGECityDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public CityDTO deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode nodes = p.getCodec().readTree(p);


        return new CityDTO(
                nodes.get("id").longValue(),
                nodes.get("nome").textValue(),
                nodes.get("microrregiao").get("mesorregiao").get("UF").get("id").longValue(),
                nodes.get("microrregiao").get("mesorregiao").get("UF").get("sigla").textValue(),
                nodes.get("microrregiao").get("mesorregiao").get("UF").get("nome").textValue()
        );
    }
}
