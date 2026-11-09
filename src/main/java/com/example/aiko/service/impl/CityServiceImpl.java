package com.example.aiko.service.impl;

import com.example.aiko.model.City;
import com.example.aiko.repository.CityRepository;
import com.example.aiko.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    @Override
    public List<City> getAllCities(){
        return cityRepository.findAll();
    }

    @Override
    public City getCity(int id) {
        return cityRepository.findById(id).get();
    }
}