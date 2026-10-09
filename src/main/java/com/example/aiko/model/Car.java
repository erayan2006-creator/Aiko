package com.example.aiko.model;

import lombok.*;

import java.util.ArrayList;
import java.util.Objects;

@Data
// @Data = @Getter, @Setter, @ToString, @EqualsAndHashCode и @RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
        cars.add(Car.builder().id(1).model("BMW").color("black").engine(3.5).year(2025).akpp("AUTO").build());
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
