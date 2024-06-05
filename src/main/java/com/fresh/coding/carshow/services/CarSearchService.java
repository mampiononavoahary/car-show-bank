package com.fresh.coding.carshow.services;

import com.fresh.coding.carshow.dtos.responses.CarWithImageSummarized;

import java.util.List;

public interface CarSearchService {
    List<CarWithImageSummarized> findCarWithImagesByCritical(String brand, String model, String typeMotor, String type, Long minPrice, Long maxPrice);
}
