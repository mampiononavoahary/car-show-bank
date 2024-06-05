package com.fresh.coding.carshow.services;

import com.fresh.coding.carshow.dtos.requests.CarRequest;
import com.fresh.coding.carshow.dtos.responses.CarSummarized;
import com.fresh.coding.carshow.dtos.responses.CarWithImageSummarized;
import com.fresh.coding.carshow.dtos.responses.Paginate;

import java.util.List;

public interface CarService {
    List<CarSummarized> createAllCars(List<CarRequest> carRequests);

    List<CarWithImageSummarized> findAllCars();

    List<String> findAllBrandOfCars(Integer limit);

    List<CarSummarized> findAllCarByStatusPinned(Integer limit);

    List<String> findAllMotorTypeOfCars();

    List<String> findAllTypeOfCars();

    CarSummarized modifyCarById(Long id, CarRequest carRequest);

    CarSummarized deleteCarById(Long id);

    Paginate<List<CarWithImageSummarized>> paginateCars(Integer page, Integer perPage);

    List<CarWithImageSummarized> findCarsByTypeAndExcludeId(String type, Long id);
}
