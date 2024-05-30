package com.fresh.coding.carshow.services.impl;

import com.fresh.coding.carshow.dtos.responses.CarResponse;
import com.fresh.coding.carshow.dtos.responses.Paginate;
import com.fresh.coding.carshow.entities.Car;
import com.fresh.coding.carshow.mappers.CarMapper;
import com.fresh.coding.carshow.repositories.CarRepository;
import com.fresh.coding.carshow.services.SearchCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SearchCarServiceImpl implements SearchCarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;


    @Override
    public Paginate<CarResponse> searchCarsByBrand(String brand, Integer pageNumber, Integer pageSize) {
        var cars = performSearchByBrand(brand, pageNumber, pageSize);
        var carResponses = cars.getContent().stream()
                .map(carMapper::toResponse)
                .collect(Collectors.toList());
        return new Paginate<>(carResponses, cars.getTotalElements(), pageNumber, pageSize);
    }

    @Override
    public Paginate<CarResponse> findCarByIntervalPrice(Long minPrice, Long maxPrice, Integer pageNumber, Integer pageSize) {
        var cars = performSearchByIntervalPrice(minPrice, maxPrice, pageNumber, pageSize);
        var carResponses = cars.getContent().stream()
                .map(carMapper::toResponse)
                .collect(Collectors.toList());
        return new Paginate<>(carResponses, cars.getTotalElements(), pageNumber, pageSize);
    }

    @Override
    public Paginate<CarResponse> searchCars(String brand, String model, Integer pageNumber, Integer pageSize) {
        var cars = performSearchByBrandAndModel(brand, model, pageNumber, pageSize);
        var carResponses = cars.getContent().stream()
                .map(carMapper::toResponse)
                .collect(Collectors.toList());
        return new Paginate<>(carResponses, cars.getTotalElements(), pageNumber, pageSize);
    }

    @Override
    public Paginate<CarResponse> searchCarsByFilters(String type, String motorType, Long minPrice, Long maxPrice, Integer pageNumber, Integer pageSize) {
        var cars = performSearchByFilters(type, motorType, minPrice, maxPrice, pageNumber, pageSize);
        var carResponses = cars.getContent().stream()
                .map(carMapper::toResponse)
                .collect(Collectors.toList());
        return new Paginate<>(carResponses, cars.getTotalElements(), pageNumber, pageSize);
    }

    private Page<Car> performSearchByFilters(String type, String motorType, Long minPrice, Long maxPrice, Integer pageNumber, Integer pageSize) {
        var pageRequest = PageRequest.of(pageNumber, pageSize);
        if (type != null && motorType != null && minPrice != null && maxPrice != null) {
            return carRepository.findByTypeAndMotorTypeAndPriceBetween(type, motorType, minPrice, maxPrice, pageRequest);
        }
        if (type != null && motorType != null && minPrice != null) {
            return carRepository.findByTypeAndMotorTypeAndPriceGreaterThanEqual(type, motorType, minPrice, pageRequest);
        }
        if (type != null && motorType != null && maxPrice != null) {
            return carRepository.findByTypeAndMotorTypeAndPriceLessThanEqual(type, motorType, maxPrice, pageRequest);
        }
        if (type != null && minPrice != null && maxPrice != null) {
            return carRepository.findByTypeAndPriceBetween(type, minPrice, maxPrice, pageRequest);
        }
        if (motorType != null && minPrice != null && maxPrice != null) {
            return carRepository.findByMotorTypeAndPriceBetween(motorType, minPrice, maxPrice, pageRequest);
        }
        if (type != null && motorType != null) {
            return carRepository.findByTypeAndMotorType(type, motorType, pageRequest);
        }
        if (type != null && minPrice != null) {
            return carRepository.findByTypeAndPriceGreaterThanEqual(type, minPrice, pageRequest);
        }
        if (type != null && maxPrice != null) {
            return carRepository.findByTypeAndPriceLessThanEqual(type, maxPrice, pageRequest);
        }
        if (motorType != null && minPrice != null) {
            return carRepository.findByMotorTypeAndPriceGreaterThanEqual(motorType, minPrice, pageRequest);
        }

        if (motorType != null && maxPrice != null) {
            return carRepository.findByMotorTypeAndPriceLessThanEqual(motorType, maxPrice, pageRequest);
        }
        if (type != null) {
            return carRepository.findByType(type, pageRequest);
        }
        if (motorType != null) {
            return carRepository.findByMotorType(motorType, pageRequest);
        }
        return extractCar(minPrice, maxPrice, pageRequest);
    }

    private Page<Car> extractCar(Long minPrice, Long maxPrice, PageRequest pageRequest) {
        if (minPrice != null && maxPrice != null) {
            return carRepository.findByPriceBetween(minPrice, maxPrice, pageRequest);
        }
        if (minPrice != null) {
            return carRepository.findByPriceGreaterThanEqual(minPrice, pageRequest);
        }
        if (maxPrice != null) {
            return carRepository.findByPriceLessThanEqual(maxPrice, pageRequest);
        }
        return carRepository.findAll(pageRequest);
    }

    private Page<Car> performSearchByBrandAndModel(String brand, String model, Integer pageNumber, Integer pageSize) {
        var pageRequest = PageRequest.of(pageNumber, pageSize);

        if (brand == null && model == null) {
            return carRepository.findAll(pageRequest);
        }
        if (brand != null && model == null) {
            return carRepository.findByBrandContainingIgnoreCase(brand, pageRequest);
        }

        if (brand == null) {
            return carRepository.findByModelContainingIgnoreCase(model, pageRequest);
        }
        return carRepository.findByBrandContainingIgnoreCaseAndModelContainingIgnoreCase(brand, model, pageRequest);
    }



    private Page<Car> performSearchByBrand(String brand, Integer pageNumber, Integer pageSize) {
        var pageRequest = PageRequest.of(pageNumber, pageSize);
        if (brand == null) {
            return carRepository.findAll(pageRequest);
        }
        return carRepository.findByBrandContainingIgnoreCase(brand, pageRequest);
    }


    private Page<Car> performSearchByIntervalPrice(Long minPrice, Long maxPrice, Integer pageNumber, Integer pageSize) {
        var pageRequest = PageRequest.of(pageNumber, pageSize);

        return extractCar(minPrice, maxPrice, pageRequest);
    }

}
