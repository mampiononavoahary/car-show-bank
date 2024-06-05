package com.fresh.coding.carshow.services.impl;

import com.fresh.coding.carshow.dtos.responses.CarWithImageSummarized;
import com.fresh.coding.carshow.mappers.CarMapper;
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
    private final CarMapper carMapper;

    @Override
    public List<CarWithImageSummarized> findCarWithImagesByCritical(
            String brand, String model, String typeMotor, String type, Long minPrice, Long maxPrice
    ) {
        if (brand == null) {
            return this.searchCarWithoutBrand(model, typeMotor, type, minPrice, maxPrice);
        }
        if (model == null) {
            return this.searchCarWithoutModel(brand, typeMotor, type, minPrice, maxPrice);
        }
        if (typeMotor == null) {
            return this.searchCarWithoutTypeMotor(brand, model, type, minPrice, maxPrice);
        }
        if (type == null) {
            return this.searchCarWithoutType(brand, model, typeMotor, minPrice, maxPrice);
        }
        if (minPrice == null) {
            return this.searchCarWithoutMinPrice(brand, model, typeMotor, type, maxPrice);
        }
        if (maxPrice == null) {
            return this.searchCarWithoutMaxPrice(brand, model, typeMotor, type, minPrice);
        }
        return List.of();
    }

    private List<CarWithImageSummarized> searchCarWithoutModel(String brand, String typeMotor, String type, Long minPrice, Long maxPrice) {
        if (brand == null){
            return this.searchCarWithoutModelAndBrand(typeMotor, type, minPrice, maxPrice);
        }
        if (typeMotor == null){
            return this.searchCarWithoutModelAndMotorType(brand, type, minPrice, maxPrice);
        }
        if (type == null){
            return this.searchCarWithoutModelAndType(brand, typeMotor, minPrice, maxPrice);
        }
        if (minPrice == null){
            return this.searchCarWithoutModelAndMinPrice(brand, typeMotor, type, maxPrice);
        }

        if (maxPrice == null){
            return this.searchCarWithoutModelAndMaxPrice(brand, typeMotor, type, minPrice);
        }
        return List.of();
    }

    private List<CarWithImageSummarized> searchCarWithoutModelAndMotorType(String brand, String type, Long minPrice, Long maxPrice) {
     return null;
    }

    private List<CarWithImageSummarized> searchCarWithoutModelAndType(String brand, String typeMotor, Long minPrice, Long maxPrice) {
        return null;
    }

    private List<CarWithImageSummarized> searchCarWithoutModelAndMinPrice(String brand, String typeMotor, String type, Long maxPrice) {
      return null;
    }

    private List<CarWithImageSummarized> searchCarWithoutModelAndMaxPrice(String brand, String typeMotor, String type, Long minPrice) {
     return null;
    }

    private List<CarWithImageSummarized> searchCarWithoutModelAndBrand(String typeMotor, String type, Long minPrice, Long maxPrice) {
      return null;
    }

    private List<CarWithImageSummarized> searchCarWithoutTypeMotor(String brand, String model, String type, Long minPrice, Long maxPrice) {
        return null;
    }

    private List<CarWithImageSummarized> searchCarWithoutType(String brand, String model, String typeMotor, Long minPrice, Long maxPrice) {
        return null;
    }

    private List<CarWithImageSummarized> searchCarWithoutMinPrice(String brand, String model, String typeMotor, String type, Long maxPrice) {
        return null;
    }

    private List<CarWithImageSummarized> searchCarWithoutMaxPrice(String brand, String model, String typeMotor, String type, Long minPrice) {
        return null;
    }

    private List<CarWithImageSummarized> searchCarWithoutBrand(String model, String typeMotor, String type, Long minPrice, Long maxPrice) {
        return null;
    }
}
