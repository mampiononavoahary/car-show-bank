package com.fresh.coding.carshow.services.impl;

import com.fresh.coding.carshow.dtos.responses.CarWithImageSummarized;
import com.fresh.coding.carshow.entities.Car;
import com.fresh.coding.carshow.mappers.CarMapper;
import com.fresh.coding.carshow.repositories.CarRepository;
import com.fresh.coding.carshow.repositories.ImageRepository;
import com.fresh.coding.carshow.services.CarSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarSearchServiceImpl implements CarSearchService {
    private final CarRepository carRepository;
    private final ImageRepository imageRepository;
    private final CarMapper carMapper;

    @Override
    public List<CarWithImageSummarized> findCarWithImagesByModelAndBrand(String brand, String model) {
        List<Car> res;
        if (brand == null && model != null) {
            res = carRepository.findAllByModelIgnoreCase(model);
        } else if (model == null && brand != null) {
            res = carRepository.findAllByBrandIgnoreCase(brand);
        } else if (brand == null) {
            res = carRepository.findAll();
        } else {
            res = carRepository.findAllByBrandIgnoreCaseAndModelIgnoreCase(brand, model);
        }
        return mapCarsToResponse(res);
    }


    @Override
    public List<CarWithImageSummarized> findCarWithImagesByType(String type) {
        List<Car> cars;
        if (type != null) {
            cars = carRepository.findAllByTypeIgnoreCase(type);
        } else {
            cars = carRepository.findAll();
        }
        return mapCarsToResponse(cars);
    }

    @Override
    public List<CarWithImageSummarized> findCarWithImagesByTypeMotor(String typeMotor) {
        List<Car> cars;
        if (typeMotor != null) {
            cars = carRepository.findAllByMotorTypeIgnoreCase(typeMotor);
        } else {
            cars = carRepository.findAll();
        }
        return mapCarsToResponse(cars);
    }

    @Override
    public List<CarWithImageSummarized> findCarWithImagesByIntervalPrice(String minPrice, String maxPrice) {
        List<Car> cars;
        if (minPrice != null && maxPrice != null) {
            cars = carRepository.findAllByPriceBetween(Long.parseLong(minPrice), Long.parseLong(maxPrice));
        } else if (minPrice != null) {
            cars = carRepository.findAllByPriceGreaterThanEqual(Long.parseLong(minPrice));
        } else if (maxPrice != null) {
            cars = carRepository.findAllByPriceLessThanEqual(Long.parseLong(maxPrice));
        } else {
            cars = carRepository.findAll();
        }
        return mapCarsToResponse(cars);
    }


    private List<CarWithImageSummarized> mapCarsToResponse(List<Car> cars) {
        return cars.stream().map(car -> {
            var images = imageRepository.findAllByCarId(car.getId());
            return carMapper.toResponse(car, images);
        }).collect(Collectors.toList());
    }

}
