package com.example.aiko.service.impl;
import com.example.aiko.model.Car;
import com.example.aiko.repository.CarRepository;
import com.example.aiko.service.CarService;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> findCarsBySearch(String word) {
        return carRepository.findCarsByWord(word);
    }

    @Override
    public Car getCar(int id) {
        return carRepository.findById(id).get();
    }

    @Override
    public void updateCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public void addCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public void deleteCar(int id) {
        carRepository.deleteById(id);
    }

    public List<Car> findCarsByYearAndColor(Integer year, String color) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> query = cb.createQuery(Car.class);
        Root<Car> root = query.from(Car.class);
        List<Predicate> predicates = new ArrayList<>();
        if(year!=null){
            predicates.add(cb.equal(root.get("year"), year));
        }
        if (color!=null && !color.isEmpty()){
            predicates.add(cb.like(root.get("color"), color));
        }
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Page<Car> findAll(Pageable pageable) {
        return carRepository.findAll(pageable);
    }
}