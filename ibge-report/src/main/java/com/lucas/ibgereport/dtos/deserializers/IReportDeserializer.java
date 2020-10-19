package com.lucas.ibgereport.dtos.deserializers;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.lucas.ibgereport.dtos.ibge.ReportDTO;

import java.io.IOException;

public class IReportDeserializer extends StdDeserializer<ReportDTO> {
    protected IReportDeserializer() {
        super((JavaType) null);
    }

    protected IReportDeserializer(Class<?> vc) {
        super(vc);
    }

    protected IReportDeserializer(JavaType valueType) {
        super(valueType);
    }

    protected IReportDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public ReportDTO deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode nodes = p.getCodec().readTree(p);

        return new ReportDTO(
                nodes.get("idEstado").longValue(),
                nodes.get("siglaEstado").textValue(),
                nodes.get("regiaoNome").textValue(),
                nodes.get("nomeCidade").textValue()
        );
    }
}
