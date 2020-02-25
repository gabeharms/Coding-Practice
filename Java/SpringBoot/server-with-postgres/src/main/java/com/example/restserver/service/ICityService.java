package com.example.restserver.service;

import com.example.restserver.model.City;
import java.util.List;

public interface ICityService {
  List<City> findAll();
  City findOne(Long id);
}
