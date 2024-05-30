package com.fresh.coding.carshow.repositories;

import com.fresh.coding.carshow.entities.Car;
import com.fresh.coding.carshow.enums.CarStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT DISTINCT c.brand FROM Car c")
    List<String> findDistinctBrands(PageRequest pageable);

    @Query("SELECT DISTINCT c.type FROM Car c")
    List<String> findAllCarTypes();

    @Query("SELECT c FROM Car c WHERE c.brand = :brand")
    List<Car> findByBrand(String brand, PageRequest pageable);

    @Query("SELECT DISTINCT c.motorType FROM Car c")
    List<String> findAllByMotorType();

    Page<Car> findByBrandContainingIgnoreCase(String brand, Pageable pageable);
    Page<Car> findByPriceBetween(Long minPrice, Long maxPrice, PageRequest pageRequest);
    Page<Car> findByPriceGreaterThanEqual(Long price, Pageable pageable);
    Page<Car> findByPriceLessThanEqual(Long price, Pageable pageable);
    List<Car> findByStatus(CarStatus carStatus, PageRequest pageable);
    Page<Car> findByModelContainingIgnoreCase(String model, PageRequest pageRequest);
    Page<Car> findByTypeAndMotorTypeAndPriceBetween(String type, String motorType, Long minPrice, Long maxPrice, Pageable pageable);
    Page<Car> findByTypeAndMotorTypeAndPriceGreaterThanEqual(String type, String motorType, Long minPrice, Pageable pageable);
    Page<Car> findByTypeAndMotorTypeAndPriceLessThanEqual(String type, String motorType, Long maxPrice, Pageable pageable);
    Page<Car> findByTypeAndPriceBetween(String type, Long minPrice, Long maxPrice, Pageable pageable);
    Page<Car> findByMotorTypeAndPriceBetween(String motorType, Long minPrice, Long maxPrice, Pageable pageable);
    Page<Car> findByTypeAndMotorType(String type, String motorType, Pageable pageable);
    Page<Car> findByTypeAndPriceGreaterThanEqual(String type, Long minPrice, Pageable pageable);
    Page<Car> findByTypeAndPriceLessThanEqual(String type, Long maxPrice, Pageable pageable);
    Page<Car> findByMotorTypeAndPriceGreaterThanEqual(String motorType, Long minPrice, Pageable pageable);
    Page<Car> findByMotorTypeAndPriceLessThanEqual(String motorType, Long maxPrice, Pageable pageable);
    Page<Car> findByType(String type, Pageable pageable);
    Page<Car> findByMotorType(String motorType, Pageable pageable);
    Page<Car> findByBrandContainingIgnoreCaseAndModelContainingIgnoreCase(String brand, String model, PageRequest pageRequest);
}
