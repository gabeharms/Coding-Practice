package com.example.restserver.controller;

import com.example.restserver.model.City;
import com.example.restserver.service.ICityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class CityController {
  
  @Autowired
  private ICityService cityService;

  @GetMapping("/cities")
  public String findCities(Model model) {
    var cities = (List<City>) cityService.findAll();

    model.addAttribute("cities", cities);

    return "showCities";
  }
}
