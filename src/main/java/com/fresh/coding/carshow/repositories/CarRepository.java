package com.fresh.coding.carshow.repositories;

import com.fresh.coding.carshow.dtos.responses.CarResponse;
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

    @Query("SELECT c FROM Car c WHERE c.brand = :brand")
    List<Car> findByBrand(String brand, PageRequest pageable);

    Page<Car> findByBrandContainingIgnoreCase(String brand, Pageable pageable);

    Page<Car> findByPriceGreaterThanEqual(Long minPrice, PageRequest pageRequest);

    Page<Car> findByPriceLessThanEqual(Long maxPrice, PageRequest pageRequest);

    Page<Car> findByBrandContainingIgnoreCaseAndPriceGreaterThanEqual(String brand, Long minPrice, PageRequest pageRequest);

    Page<Car> findByBrandContainingIgnoreCaseAndPriceLessThanEqual(String brand, Long maxPrice, PageRequest pageRequest);

    Page<Car> findByPriceBetween(Long minPrice, Long maxPrice, PageRequest pageRequest);

    Page<Car> findByBrandContainingIgnoreCaseAndPriceBetween(String brand, Long minPrice, Long maxPrice, PageRequest pageRequest);

    List<Car> findByStatus(CarStatus carStatus, PageRequest pageable);
}
