package com.example.restserver.controller;

import com.example.restserver.model.City;
import com.example.restserver.service.ICityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class CityController {
  
  @Autowired
  private ICityService cityService;

  @GetMapping("/cities")
  public List<City> findCities() {
    var cities = (List<City>) cityService.findAll();

    return cities;
  }

  @GetMapping("/cities/{id}")
  public City findCity(@PathVariable String id) {
    City city =  cityService.findOne(Long.parseLong(id));

    return city;
  }
}
