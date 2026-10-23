package com.example.aiko.controller;

import com.example.aiko.model.Car;
import com.example.aiko.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final CarRepository carRepository;
    @GetMapping
    public String getHomePage(Model model){
        model.addAttribute("cars", carRepository.findAll());
        return "index";
    }
    @PostMapping(value = "/add")
    public String addCar(Car car, Model model){
        carRepository.save(car);
        return "redirect:/";
    }
    @GetMapping(value = "/add-page")
    public String addCarPage(){
        return "add-page";
    }
    @GetMapping(value = "/details/{id}")
    public String getCarDetails(@PathVariable Integer id, Model model){
        model.addAttribute("car", carRepository.findById(id).get());
        return "details-car";
    }
    /*
    @GetMapping(value = "/details")
    public String getCarDetails(@RequestParam Integer id, Model model){
        model.addAttribute("car", Car.getCarById(id));
        return "details-car";
    }
    */

    @PostMapping(value = "/update-car")
    public String updateCar(Car car){
        carRepository.save(car);
        return "redirect:/";
    }

    @PostMapping(value = "/delete")
    public String deleteCar(@RequestParam int id){
        carRepository.deleteById(id);
        return "redirect:/";
    }
}
