package com.lucas.ibgereport.services.city.interfaces;


public interface ICityService<T> {
    T getCityByRegion(String abbreviation) throws Exception;
}
