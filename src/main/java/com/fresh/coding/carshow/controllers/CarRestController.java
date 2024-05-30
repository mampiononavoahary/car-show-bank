package com.fresh.coding.carshow.controllers;

import com.fresh.coding.carshow.dtos.requests.CarRequest;
import com.fresh.coding.carshow.dtos.responses.CarResponse;
import com.fresh.coding.carshow.dtos.responses.Paginate;
import com.fresh.coding.carshow.services.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarRestController {

    private final CarService carService;

    @PostMapping
    public List<CarResponse> createAllCars(
            @RequestBody
            List<@Valid CarRequest> carRequests) {
        return carService.createAllCars(carRequests);
    }

    @GetMapping
    public Paginate<CarResponse> searchCars (
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Long minPrice,
            @RequestParam(required = false) Long maxPrice,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        return carService.searchCars(brand, minPrice, maxPrice, pageNumber, pageSize);
    }

    @GetMapping("/pinned")
    public List<CarResponse> getPinnedCars (
            @RequestParam(defaultValue = "6") String limit
    ) {
        return carService.findPinnedCars(Integer.valueOf(limit));
    }
}
