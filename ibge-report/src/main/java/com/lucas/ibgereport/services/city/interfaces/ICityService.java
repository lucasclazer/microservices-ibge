package com.lucas.ibgereport.services.city.interfaces;

import java.io.IOException;

public interface ICityService<T> {
    public T getCityByRegion(String abbreviation) throws Exception;
}
