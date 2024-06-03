package com.fresh.coding.carshow.services.impl;

import com.fresh.coding.carshow.dtos.requests.CarRequest;
import com.fresh.coding.carshow.dtos.responses.CarSummarized;
import com.fresh.coding.carshow.dtos.responses.CarWithImageSummarized;
import com.fresh.coding.carshow.entities.Car;
import com.fresh.coding.carshow.mappers.CarMapper;
import com.fresh.coding.carshow.repositories.CarRepository;
import com.fresh.coding.carshow.repositories.ImageRepository;
import com.fresh.coding.carshow.services.CarService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ImageRepository imageRepository;
    private final CarMapper carMapper;


    @Transactional
    @Override
    public List<CarSummarized> createAllCars(List<CarRequest> carRequests) {
        var carsSaved = new ArrayList<CarSummarized>();
        for (var carRequest : carRequests) {
            var car = carMapper.toEntity(carRequest);
            var saved = carRepository.save(car);
            var carRes = carMapper.toResponse(saved);
            carsSaved.add(carRes);
        }
        return carsSaved;
    }

    @Override
    public List<CarWithImageSummarized> findAllCars() {
        var cars = carRepository.findAll();
        var carsWithImagesSummarized = new ArrayList<CarWithImageSummarized>();

        for (var car : cars) {
            var carWithImage = this.newCarWithImage(car);
            carsWithImagesSummarized.add(carWithImage);
        }
        return carsWithImagesSummarized;
    }

    private CarWithImageSummarized newCarWithImage(Car car) {
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
