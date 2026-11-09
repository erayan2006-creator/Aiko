package com.example.aiko.controller;

import com.example.aiko.model.Car;
import com.example.aiko.model.City;
import com.example.aiko.service.CarService;
import com.example.aiko.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/car")
@RequiredArgsConstructor
public class CarRestController {
    private final CarService carService;
    private final CityService cityService;
    @GetMapping(value = "/")
    public List<Car> getAllCars(){
        return carService.getAllCars();
    }
    @GetMapping(value = "/get-cities")
    public List<City> getAllCities(){
        return cityService.getAllCities();
    }
    @GetMapping(value = "/get-car/{id}")
    public Car getCarById(@PathVariable int id){
        return carService.getCar(id);
    }
    @GetMapping(value = "/search")
    public List<Car> getCarsByWord(@RequestParam String word){
        return carService.findCarsBySearch(word);
    }
    @PostMapping(value = "/add-car")
    public void addCar(@RequestBody Car car){
        carService.addCar(car);
    }
    @PutMapping(value = "/update-car")
    public void updateCar(@RequestBody Car car){
        carService.updateCar(car);
    }
    @DeleteMapping(value = "/delete")
    public void deleteCar(@RequestParam int id){
        carService.deleteCar(id);
    }
    @PatchMapping(value = "/delete-city")
    public void deleteCity(@RequestParam int city_id, @RequestParam int car_id){
        Car car = carService.getCar(car_id);
        City city = cityService.getCity(city_id);
        car.getCities().remove(city);
        carService.updateCar(car);
    }
    @PatchMapping(value = "/add-city")
    public void addCity(@RequestParam int city_id, @RequestParam int car_id){
        Car car = carService.getCar(car_id);
        City city = cityService.getCity(city_id);
        car.getCities().add(city);
        carService.updateCar(car);
    }
    @GetMapping(value = "/year-color")
    public List<Car> getCarsByYearAndColor(Model model, @RequestParam(required = false) String color, @RequestParam(required = false) Integer year){
        return carService.findCarsByYearAndColor(year, color);
    }
    @GetMapping(value = "/sort-pagination")
    public List<Car> getAllCarsByPagination(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size, @RequestParam(defaultValue = "year") int param, @RequestParam(defaultValue = "asc") String direction){
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(String.valueOf(param)).descending(): Sort.by(String.valueOf(param)).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Car> carPage = carService.findAll(pageable);
        return carPage.stream().toList();
    }
}