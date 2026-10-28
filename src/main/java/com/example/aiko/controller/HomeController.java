package com.example.aiko.controller;

import com.example.aiko.model.Car;
import com.example.aiko.repository.CarOwnerRepository;
import com.example.aiko.repository.CarRepository;
import com.example.aiko.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.aiko.model.City;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final CarRepository carRepository;
    private final CarOwnerRepository carOwnerRepository;
    private final CityRepository cityRepository;
    @GetMapping
    public String getHomePage(Model model){
        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("cities", cityRepository.findAll());
        return "index";
    }
    @PostMapping("/add")
    public String addCar(
            Car car,
            @RequestParam(value = "cityIds", required = false)
            List<Integer> cityIds) {

        List<City> selectedCities = cityIds == null
                ? new ArrayList<>()
                : cityRepository.findAllById(cityIds);

        car.setCities(selectedCities);
        carRepository.save(car);

        return "redirect:/";
    }
    @GetMapping("/add-page")
    public String addCarPage(Model model) {
        model.addAttribute("cities", cityRepository.findAll());
        return "add-page";
    }

    @GetMapping(value = "/details/{id}")
    public String getCarDetails(@PathVariable Integer id, Model model){
        Car car = carRepository.findById(id).orElseThrow();
        model.addAttribute("car", carRepository.findById(id).get());
        model.addAttribute("cities", cityRepository.findAll());
        return "details-car";
    }

    @PostMapping("/update-car")
    public String updateCar(
            Car car,
            @RequestParam(value = "cityIds", required = false)
            List<Integer> cityIds) {

        List<City> selectedCities = cityIds == null
                ? new ArrayList<>()
                : cityRepository.findAllById(cityIds);

        car.setCities(selectedCities);
        carRepository.save(car);

        return "redirect:/";
    }

    @PostMapping(value = "/delete")
    public String deleteCar(@RequestParam int id){
        carRepository.deleteById(id);
        return "redirect:/";
    }
}
