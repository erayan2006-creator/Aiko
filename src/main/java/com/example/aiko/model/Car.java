package com.example.aiko.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// Эти 4 команды создали для нас конструкторы и геттеры, сеттеры
public class Car {
    private Integer id;
    private String model;
    private String color;
    private double engine;
    private int year;
    private String akpp;
    public static ArrayList<Car> cars = new ArrayList<>();
    private static Integer idCar = 4;
    static {
        cars.add(new Car(1, "BMW", "black", 3.5, 2025, "AUTO"));
        cars.add(new Car(2, "TOYOTA", "white", 3, 2026, "AUTO"));
        cars.add(new Car(3, "KIA", "black", 1.5, 2020, "AUTO"));
    }

    public static void addCar(Car car) {
        car.setId(idCar);
        cars.add(car);
        idCar++;
    }

    public static Car getCarById(Integer id) {

        return cars.stream().filter(c-> Objects.equals(c.getId(), id)).findFirst().orElseThrow();
    }

    public static void updateCar(Car car) {

        for(Car carFromBase: cars){
            if(Objects.equals(carFromBase.getId(), car.getId())){
                carFromBase.setAkpp(car.getAkpp());
                carFromBase.setColor(car.getColor());
                carFromBase.setYear(car.getYear());
                carFromBase.setEngine(car.getEngine());
                carFromBase.setModel(car.getModel());
            }
        }

    }

    public static void deleteCar(int id) {
        cars.removeIf(car -> Objects.equals(car.getId(), id));
    }
}
