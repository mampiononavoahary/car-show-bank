package com.fresh.coding.carshow.services.impl;

import com.fresh.coding.carshow.dtos.requests.CarRequest;
import com.fresh.coding.carshow.dtos.responses.CarSummarized;
import com.fresh.coding.carshow.dtos.responses.CarWithImageSummarized;
import com.fresh.coding.carshow.enums.CarStatus;
import com.fresh.coding.carshow.exceptions.NotFoundException;
import com.fresh.coding.carshow.mappers.CarMapper;
import com.fresh.coding.carshow.repositories.CarRepository;
import com.fresh.coding.carshow.repositories.ImageRepository;
import com.fresh.coding.carshow.services.CarService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            var images = imageRepository.findAllByCarId(car.getId());
            var carWithImage = carMapper.toResponse(car, images);
            carsWithImagesSummarized.add(carWithImage);
        }
        return carsWithImagesSummarized;
    }

    @Override
    public List<String> findAllBrandOfCars(Integer limit) {
        var page = PageRequest.of(0, limit);
        return carRepository.findAllBrand(page).getContent();
    }

    @Override
    public List<CarSummarized> findAllCarByStatusPinned(Integer limit) {
        var page = PageRequest.of(0, limit);
        var cars =  carRepository.findByStatus(CarStatus.PINNED,page).getContent();
        return cars.stream()
                .map(carMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllMotorTypeOfCars() {
        return carRepository.findAllMotorTypeOfCars();
    }

    @Override
    public List<String> findAllTypeOfCars() {
        return carRepository.findAllTypeOfCars();
    }

    @Override
    public CarSummarized modifyCarById(Long id, CarRequest carRequest) {
        var car = carRepository.findById(id).orElseThrow(() -> new NotFoundException("Car not found"));
        var newCar = carMapper.toEntity(carRequest);
        newCar.setId(car.getId());
        var savedCar = carRepository.save(newCar);
        return carMapper.toResponse(savedCar);
    }

    @Override
    public CarSummarized deleteCarById(Long id) {
        var car = carRepository.findById(id).orElseThrow(() -> new NotFoundException("Car not found"));
        carRepository.deleteById(car.getId());
        return carMapper.toResponse(car);
    }


}
