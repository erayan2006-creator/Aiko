package com.example.aiko.controller;

import com.example.aiko.model.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @GetMapping
    public String getHomePage(Model model){
        model.addAttribute("mashini", Car.cars);
        return "index";
    }
    @PostMapping(value = "/add")
    public String addCar(Car car){
        Car.addCar(car);
        return "redirect:/";
    }
    @GetMapping(value = "/add-page")
    public String addCarPage(){
        return "add-page";
    }
    @GetMapping(value = "/details/{id}")
    public String getCarDetails(@PathVariable Integer id, Model model){
        model.addAttribute("car", Car.getCarById(id));
        return "details-car";
    }

    @PostMapping(value = "/update-car")
    public String updateCar(Car car){

        Car.updateCar(car);

        return "redirect:/";
    }

    @PostMapping(value = "/delete")
    public String deleteCar(@RequestParam int id){
        Car.deleteCar(id);
        return "redirect:/";
    }
}
