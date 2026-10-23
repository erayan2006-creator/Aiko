package com.example.aiko.repository;

import com.example.aiko.model.Car;
import com.example.aiko.model.CarOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarOwnerRepository extends JpaRepository<CarOwner, Integer> {
}
