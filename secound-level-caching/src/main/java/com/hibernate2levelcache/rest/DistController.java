package com.hibernate2levelcache.rest;


import com.hibernate2levelcache.entity.City;
import com.hibernate2levelcache.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DistController {
    @Autowired
    private CityService cityService;

    @GetMapping("/dist/{name}")
    public ResponseEntity<City> getDistByName(@PathVariable(name = "name") String name){
        return new ResponseEntity<City>(cityService.getDistByName(name), HttpStatus.OK);
    }

}
