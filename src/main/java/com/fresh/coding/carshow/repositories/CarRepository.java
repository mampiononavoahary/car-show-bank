package com.fresh.coding.carshow.repositories;

import com.fresh.coding.carshow.entities.Car;
import com.fresh.coding.carshow.enums.CarStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("SELECT DISTINCT c.brand FROM Car c")
    Page<String> findAllBrand(Pageable pageable);

    Page<Car> findByStatus(CarStatus status, Pageable pageable);

    @Query("SELECT DISTINCT c.motorType FROM Car c")
    List<String> findAllMotorTypeOfCars();

    @Query("SELECT DISTINCT c.type FROM Car c")
    List<String> findAllTypeOfCars();
}
