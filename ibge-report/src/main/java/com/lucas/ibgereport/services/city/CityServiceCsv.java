package com.lucas.ibgereport.services.city;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.lucas.ibgereport.dtos.ibge.ReportDTO;
import com.lucas.ibgereport.services.city.interfaces.ICityService;
import com.lucas.ibgereport.thirdparties.ibge.IIBGECity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class CityServiceCsv implements ICityService {
    private final IIBGECity iibgeCity;

    @Autowired
    public CityServiceCsv(IIBGECity iibgeCity) {
        this.iibgeCity = iibgeCity;
    }

    @Override
    public ByteArrayOutputStream getCityByRegion(String abbreviation) throws IOException {
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

        return outputStream;
    }
}
