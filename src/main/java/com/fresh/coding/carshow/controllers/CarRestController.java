package com.fresh.coding.carshow.controllers;

import com.fresh.coding.carshow.dtos.requests.CarRequest;
import com.fresh.coding.carshow.dtos.responses.CarResponse;
import com.fresh.coding.carshow.dtos.responses.Paginate;
import com.fresh.coding.carshow.services.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarRestController {

    private final CarService carService;

    @GetMapping
    public Paginate<CarResponse> getCars(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        var pageable = PageRequest.of(page, size);
        return carService.getAllCars(pageable);
    }

    @PostMapping
    public List<CarResponse> createAllCars(
            @RequestBody
            List<@Valid CarRequest> carRequests){
        return carService.createAllCars(carRequests);
    }
}
