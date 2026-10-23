package com.example.aiko.repository;

import com.example.aiko.model.Car;
import com.example.aiko.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
}
