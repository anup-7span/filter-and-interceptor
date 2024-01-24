package com.hibernate2levelcache.service;

import com.hibernate2levelcache.entity.City;
import com.hibernate2levelcache.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public City getCityById(Integer id){
        return cityRepository.findById(id).get();
    }

    public City saveCity(City city){
        return cityRepository.save(city);
    }

    public City getDistByName(String name) {
        return cityRepository.findByName(name);
    }
}
