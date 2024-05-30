package com.fresh.coding.carshow.services;

import com.fresh.coding.carshow.dtos.requests.CarRequest;
import com.fresh.coding.carshow.dtos.responses.CarResponse;
import com.fresh.coding.carshow.dtos.responses.Paginate;

import java.util.List;

public interface CarService {

    List<CarResponse> createAllCars(List<CarRequest> carRequests);

    Paginate<CarResponse> searchCars(String brand, Long minPrice, Long maxPrice, Integer pageNumber, Integer pageSize);

    List<CarResponse> findPinnedCars(Integer limit);
}
