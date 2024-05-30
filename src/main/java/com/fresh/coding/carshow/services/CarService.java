package com.fresh.coding.carshow.services;

import com.fresh.coding.carshow.dtos.requests.CarRequest;
import com.fresh.coding.carshow.dtos.responses.CarResponse;

import java.util.List;

public interface CarService {

    List<CarResponse> createAllCars(List<CarRequest> carRequests);


    List<CarResponse> findPinnedCars(Integer limit);

    CarResponse findById(Long id);

    List<String> findAllByCarType();

    List<String> findAllByMotorType();

}
