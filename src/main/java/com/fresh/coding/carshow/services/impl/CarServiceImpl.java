package com.fresh.coding.carshow.services.impl;

import com.fresh.coding.carshow.dtos.requests.CarRequest;
import com.fresh.coding.carshow.dtos.responses.CarResponse;
import com.fresh.coding.carshow.enums.CarStatus;
import com.fresh.coding.carshow.exceptions.NotFoundException;
import com.fresh.coding.carshow.mappers.CarMapper;
import com.fresh.coding.carshow.repositories.CarRepository;
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
    private final CarMapper carMapper;


    @Transactional
    @Override
    public List<CarResponse> createAllCars(List<CarRequest> carRequests) {
        var carsSaved = new ArrayList<CarResponse>();
        for (var carRequest : carRequests) {
            var car = carMapper.toEntity(carRequest);
            var saved = carRepository.save(car);
            var carRes = carMapper.toResponse(saved);
            carsSaved.add(carRes);
        }
        return carsSaved;
    }


    @Override
    public List<CarResponse> findPinnedCars(Integer limit) {
        var pageable = PageRequest.of(0, limit);
        return carRepository.findByStatus(CarStatus.PINNED, pageable)
                .stream()
                .map(carMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CarResponse findById(Long id) {
        var savedCar = carRepository.findById(id).orElseThrow(() -> new NotFoundException("Car not found"));
        return carMapper.toResponse(savedCar);
    }

    @Override
    public List<String> findAllByCarType() {
        return carRepository.findAllCarTypes();
    }

    @Override
    public List<String> findAllByMotorType() {
        return carRepository.findAllByMotorType();
    }

}
