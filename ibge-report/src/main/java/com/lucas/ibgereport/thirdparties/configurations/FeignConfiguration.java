package com.lucas.ibgereport.thirdparties.configurations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {
    @Bean
    public ObjectMapper mapper(){
        return new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .configure(SerializationFeature.INDENT_OUTPUT, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Bean
    public OkHttpClient client(){
        return new OkHttpClient();
    }

    @Bean
    public Encoder encoder(ObjectMapper mapper){
        return new JacksonEncoder(mapper);
    }

    @Bean
    public Decoder decoder(ObjectMapper mapper){
        return new JacksonDecoder(mapper);
    }

    @Bean
    public Logger feignLogger(){
        return new Slf4jLogger();
    }

}
