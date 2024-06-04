package com.fresh.coding.carshow.services.impl;

import com.fresh.coding.carshow.dtos.responses.CarWithImageSummarized;
import com.fresh.coding.carshow.repositories.CarRepository;
import com.fresh.coding.carshow.repositories.ImageRepository;
import com.fresh.coding.carshow.services.CarSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarSearchServiceImpl implements CarSearchService {
    private final CarRepository carRepository;
    private final ImageRepository imageRepository;

    @Override
    public List<CarWithImageSummarized> findCarWithImagesByCritical(String brand, String model, String typeMotor, String type, Long minPrice, Long maxPrice) {
        return List.of();
    }
}
