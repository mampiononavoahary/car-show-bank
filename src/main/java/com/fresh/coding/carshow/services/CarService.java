package com.fresh.coding.carshow.services;

import com.fresh.coding.carshow.dtos.requests.CarRequest;
import com.fresh.coding.carshow.dtos.responses.CarResponse;
import com.fresh.coding.carshow.dtos.responses.Paginate;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CarService {

    List<CarResponse> createAllCars(List<CarRequest> carRequests);

    Paginate<CarResponse> getAllCars(PageRequest pageable);
}
