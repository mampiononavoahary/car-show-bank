package com.fresh.coding.carshow.services;

import com.fresh.coding.carshow.dtos.requests.CarRequest;
import com.fresh.coding.carshow.dtos.responses.CarSummarized;
import com.fresh.coding.carshow.dtos.responses.CarWithImageSummarized;

import java.util.List;

public interface CarService {
    List<CarSummarized> createAllCars(List<CarRequest> carRequests);

    List<CarWithImageSummarized> findAllCars();
}
