package com.example.aiko.service;

import com.example.aiko.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {
    List<Car> getAllCars();
    List<Car> findCarsBySearch(String word);
    Car getCar(int id);
    void updateCar(Car car);
    void addCar(Car car);
    void deleteCar(int id);
    List<Car> findCarsByYearAndColor(Integer year, String color);
    Page<Car> findAll(Pageable pageable);
}