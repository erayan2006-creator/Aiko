package com.example.aiko.repository;

import com.example.aiko.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    @Query("SELECT st FROM Car st WHERE st.model ilike concat('%', :word, '%') ")
    List<Car> findCarsByWord(String word);

    Page<Car> findAll(Pageable pageable);
}
