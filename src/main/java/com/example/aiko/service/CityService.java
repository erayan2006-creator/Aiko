package com.example.aiko.service;

import com.example.aiko.model.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityService {
    List<City> getAllCities();
    City getCity(int id);
}