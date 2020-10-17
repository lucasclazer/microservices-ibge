package com.lucas.ibgereport.services;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.lucas.ibgereport.dtos.ibge.CityDTO;
import com.lucas.ibgereport.dtos.ibge.ReportDTO;
import com.lucas.ibgereport.thirdparties.ibge.IIBGECity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CityService {
    private final IIBGECity iibgeCity;

    @Autowired
    public CityService(IIBGECity iibgeCity) {
        this.iibgeCity = iibgeCity;
    }

    public Long getCityIdByName(String cityName){
        Collection<CityDTO> cities = this.iibgeCity.getCitiesFromBrazil();
        var city = cities.stream().filter((value) -> {
            return value.getName().toUpperCase().contains(cityName.toUpperCase());
        }).findFirst();

        if (city.isPresent())
            return city.get().getId();

        return 0L;
    }

    public Collection<ReportDTO> getCityByRegion(String abbreviation){
        return iibgeCity.getByRegion(abbreviation).stream().map( c -> new ReportDTO(
                c.getIdRegion(),
                c.getRegionAbbreviation(),
                c.getRegionName(),
                c.getName()
        )).collect(Collectors.toCollection(ArrayList<ReportDTO>::new));
    }

    public ByteArrayOutputStream getCityByRegionCSV(String abbreviation) throws IOException {
        var cities = iibgeCity.getByRegion(abbreviation);

        var reportCities = cities.stream().map(c -> new ReportDTO(
                c.getIdRegion(),
                c.getRegionAbbreviation(),
                c.getRegionName(),
                c.getName()
                )).toArray();

        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(ReportDTO.class);
        schema = schema.withHeader().withColumnSeparator(',');

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ObjectWriter myObjectWriter = mapper.writer(schema);
        myObjectWriter.writeValue(outputStream, reportCities);

//        byte[] bytes = new byte[outputStream.size()];
//
//        outputStream.write(bytes);
//
//        InputStream inputStream = new ByteArrayInputStream(bytes);

//        File file = new File("cities.csv");
//        myObjectWriter.writeValue(file, reportCities);

//        InputStream inputStream = new ByteArrayInputStream(bytes);
//        FileOutputStream fileOutputStream = new FileOutputStream("cities.csv");
//        myObjectWriter.writeValue(fileOutputStream, reportCities);
//        InputStream inputStream = new InputStreamReader(fileOutputStream);
//
        return outputStream;
    }

}
