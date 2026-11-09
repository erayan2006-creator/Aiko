package com.example.aiko.controller;

import com.example.aiko.model.Car;
import com.example.aiko.repository.CarOwnerRepository;
import com.example.aiko.repository.CarRepository;
import com.example.aiko.repository.CityRepository;
import com.example.aiko.service.CarOwnerService;
import com.example.aiko.service.CarService;
import com.example.aiko.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.aiko.model.City;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final CarService carService;
    private final CityService cityService;
    private final CityRepository cityRepository;
    @GetMapping
    public String getHomePage(Model model){
        model.addAttribute("cars", carService.getAllCars());
        model.addAttribute("cities", cityService.getAllCities());
        return "index";
    }
    @GetMapping(value = "/search")
    public String getCarsByWord(@RequestParam String word, Model model){
        model.addAttribute("cars", carService.findCarsBySearch(word));
        model.addAttribute("cities", cityService.getAllCities());

        return "index";
    }
    @PostMapping("/add")
    public String addCar(Car car, @RequestParam(value = "cityIds", required = false) List<Integer> cityIds) {
        List<City> selectedCities = cityIds == null
                ? new ArrayList<>()
                : cityRepository.findAllById(cityIds);

        car.setCities(selectedCities);
        carService.addCar(car);

        return "redirect:/";
    }
    @GetMapping("/add-page")
    public String addCarPage(Model model) {
        model.addAttribute("cities", cityService.getAllCities());
        return "add-page";
    }

    @GetMapping(value = "/details/{id}")
    public String detailsPage(@PathVariable Integer id, Model model){
        Car car = carService.getCar(id);
        List<City> cities = cityService.getAllCities();
        cities.removeAll(car.getCities());
        model.addAttribute("car", carService.getCar(id));
        model.addAttribute("cities", cityService.getAllCities());
        return "details-car";
    }

    @PostMapping("/update-car")
    public String updateCar(Car car, @RequestParam(value = "cityIds", required = false) List<Integer> cityIds) {
        List<City> selectedCities = cityIds == null
                ? new ArrayList<>()
                : cityRepository.findAllById(cityIds);

        car.setCities(selectedCities);
        carService.updateCar(car);

        return "redirect:/";
    }

    @PostMapping(value = "/delete")
    public String deleteCar(Integer id){
        carService.deleteCar(id);
        return "redirect:/";
    }
    @PostMapping(value = "/delete-city")
    public String deleteCity(@RequestParam int city_id, @RequestParam int car_id){
        Car car = carService.getCar(car_id);
        City city = cityService.getCity(city_id);
        car.getCities().remove(city);
        carService.updateCar(car);
        return "redirect:/details-car/" + car_id;
    }
    @PostMapping(value = "/add-city")
    public String addCity(@RequestParam int city_id, @RequestParam int car_id){
        Car car = carService.getCar(car_id);
        City city = cityService.getCity(city_id);
        car.getCities().add(city);
        carService.updateCar(car);
        return "redirect:/details-car/" + car_id;
    }
    @GetMapping(value = "/year-color")
    public String getCarsByYearAndColor(Model model, @RequestParam(required = false) String color, @RequestParam(required = false) Integer year){
        model.addAttribute("cars", carService.findCarsByYearAndColor(year, color));
        return "index";
    }
    @GetMapping(value = "/sort-pagination")
    public String getAllCarsByPagination(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size, @RequestParam(defaultValue = "year") String param, @RequestParam(defaultValue = "asc") String direction){
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by((param)).descending():Sort.by((param)).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Car> carPage = carService.findAll(pageable);
        model.addAttribute("cars", carPage);
        return "index";
    }
}
