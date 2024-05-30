package com.fresh.coding.carshow.services;

import com.fresh.coding.carshow.dtos.responses.CarResponse;
import com.fresh.coding.carshow.dtos.responses.Paginate;

public interface SearchCarService {
    Paginate<CarResponse> searchCarsByBrand(String brand, Integer pageNumber, Integer pageSize);

    Paginate<CarResponse> findCarByIntervalPrice(Long minPrice, Long maxPrice, Integer pageNumber, Integer pageSize);

    Paginate<CarResponse> searchCars(String brand, String model, Integer pageNumber, Integer pageSize);
    Paginate<CarResponse> searchCarsByFilters(String type, String motorType, Long minPrice, Long maxPrice, Integer pageNumber, Integer pageSize);
}
