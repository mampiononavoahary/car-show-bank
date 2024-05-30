package com.fresh.coding.carshow.services.impl;

import com.fresh.coding.carshow.dtos.requests.CarRequest;
import com.fresh.coding.carshow.dtos.responses.CarResponse;
import com.fresh.coding.carshow.dtos.responses.Paginate;
import com.fresh.coding.carshow.mappers.CarMapper;
import com.fresh.coding.carshow.repositories.CarRepository;
import com.fresh.coding.carshow.services.CarService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class CarServiceImpl implements CarService  {

    private final CarRepository carRepository;
    private final CarMapper carMapper;


    @Transactional
    @Override
    public List<CarResponse> createAllCars(List<CarRequest> carRequests) {
        var carsSaved = new ArrayList<CarResponse>();
        for (var carRequest: carRequests){
            var car = carMapper.toEntity(carRequest);
            var saved = carRepository.save(car);
            var carRes = carMapper.toResponse(saved);
            carsSaved.add(carRes);
        }
        return carsSaved;
    }

    @Override
    public Paginate<CarResponse> getAllCars(PageRequest pageable) {
        var carPage  = carRepository.findAll(pageable);
        var carResponse = carPage.map(carMapper::toResponse).getContent();
        return new Paginate<>(
                carResponse,
                carPage.getTotalElements(),
                carPage.getNumber(),
                carPage.getSize()
        );
    }
}
