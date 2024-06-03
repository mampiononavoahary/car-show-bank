package com.fresh.coding.carshow.services.impl;

import com.fresh.coding.carshow.dtos.responses.CarWithImageSummarized;
import com.fresh.coding.carshow.exceptions.NotFoundException;
import com.fresh.coding.carshow.repositories.CarRepository;
import com.fresh.coding.carshow.repositories.ImageRepository;
import com.fresh.coding.carshow.services.CarWithImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarWithImageServiceImpl implements CarWithImageService {
    private final CarRepository carRepository;
    private final ImageRepository imageRepository;

    @Override
    public CarWithImageSummarized findCarWithImage(Long id) {
        var car = carRepository.findById(id).orElseThrow(() -> new NotFoundException("Car not found !"));
        var images = imageRepository.findAllByCarId(car.getId());
        return new CarWithImageSummarized(
                car.getId(),
                car.getName(),
                car.getDescription(),
                car.getBrand(),
                car.getModel(),
                car.getPrice(),
                car.getColor(),
                car.getMotorType(),
                car.getType(),
                car.getPower(),
                car.getPlaceNumber(),
                car.getStatus(),
                images
        );
    }
}
