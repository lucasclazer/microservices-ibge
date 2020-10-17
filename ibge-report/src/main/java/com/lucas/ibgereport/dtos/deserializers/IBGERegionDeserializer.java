package com.lucas.ibgereport.dtos.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.lucas.ibgereport.dtos.ibge.RegionDTO;

import java.io.IOException;

public class IBGERegionDeserializer extends StdDeserializer<RegionDTO>{
    protected IBGERegionDeserializer(Class<?> vc) {
        super(vc);
    }

    public IBGERegionDeserializer(){
        this((JavaType) null);
    }

    protected IBGERegionDeserializer(JavaType valueType) {
        super(valueType);
    }

    protected IBGERegionDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public RegionDTO deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode nodes = parser.getCodec().readTree(parser);

        return
                new RegionDTO(
                        nodes.get("id").longValue(),
                        nodes.get("sigla").textValue(),
                        nodes.get("nome").textValue()
                );

    }
}
