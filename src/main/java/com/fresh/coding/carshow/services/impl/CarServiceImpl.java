package com.fresh.coding.carshow.services.impl;

import com.fresh.coding.carshow.dtos.requests.CarRequest;
import com.fresh.coding.carshow.dtos.responses.CarResponse;
import com.fresh.coding.carshow.dtos.responses.Paginate;
import com.fresh.coding.carshow.entities.Car;
import com.fresh.coding.carshow.enums.CarStatus;
import com.fresh.coding.carshow.mappers.CarMapper;
import com.fresh.coding.carshow.repositories.CarRepository;
import com.fresh.coding.carshow.services.CarService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public Paginate<CarResponse> searchCars(String brand, Long minPrice, Long maxPrice, Integer pageNumber, Integer pageSize) {
        var cars = performSearch(brand, minPrice, maxPrice, pageNumber, pageSize);
        var carResponses = cars.getContent().stream()
                .map(carMapper::toResponse)
                .collect(Collectors.toList());
        return new Paginate<>(carResponses, cars.getTotalElements(), pageNumber, pageSize);
    }


    @Override
    public List<CarResponse> findPinnedCars(Integer limit) {
        var pageable = PageRequest.of(0, limit);
        return carRepository.findByStatus(CarStatus.PINNED, pageable)
                .stream()
                .map(carMapper::toResponse)
                .collect(Collectors.toList());
    }

    private Page<Car> performSearch(String brand, Long minPrice, Long maxPrice, Integer pageNumber, Integer pageSize) {
        var pageRequest = PageRequest.of(pageNumber, pageSize);
        if (brand == null && minPrice == null && maxPrice == null){
            return carRepository.findAll(pageRequest);
        }else if (maxPrice == null && minPrice == null) {
            return carRepository.findByBrandContainingIgnoreCase(brand, pageRequest);
        } else if (maxPrice == null && brand == null) {
            return carRepository.findByPriceGreaterThanEqual(minPrice, pageRequest);
        } else if (minPrice == null && brand == null) {
            return carRepository.findByPriceLessThanEqual(maxPrice, pageRequest);
        } else if (maxPrice == null) {
            return carRepository.findByBrandContainingIgnoreCaseAndPriceGreaterThanEqual(brand, minPrice, pageRequest);
        } else if (minPrice == null) {
            return carRepository.findByBrandContainingIgnoreCaseAndPriceLessThanEqual(brand, maxPrice, pageRequest);
        } else if (brand == null) {
            return carRepository.findByPriceBetween(minPrice, maxPrice, pageRequest);
        } else {
            return carRepository.findByBrandContainingIgnoreCaseAndPriceBetween(brand, minPrice, maxPrice, pageRequest);
        }
    }

}
