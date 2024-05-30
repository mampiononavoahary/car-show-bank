package com.fresh.coding.carshow.dtos.responses;

import com.fresh.coding.carshow.entities.Car;

import java.util.List;

public record BrandWithCars(
        String brand,
        List<Car> cars
) {}
