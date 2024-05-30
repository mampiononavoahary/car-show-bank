package com.fresh.coding.carshow.controllers;

import com.fresh.coding.carshow.dtos.responses.CarResponse;
import com.fresh.coding.carshow.dtos.responses.Paginate;
import com.fresh.coding.carshow.services.SearchCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars/search")
public class SearchCarRestController {

    private final SearchCarService searchCarService;

    @GetMapping("/brand")
    public Paginate<CarResponse> searchCarsByBrand(
            @RequestParam(required = false) String brand,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        return searchCarService.searchCarsByBrand(brand, pageNumber, pageSize);
    }
    @GetMapping("/")
    public Paginate<CarResponse> searchCars(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return searchCarService.searchCars(brand, model, pageNumber, pageSize);
    }

    @GetMapping("/interval/price")
    public Paginate<CarResponse> getCarByIntervalPrice(
            @RequestParam(required = false) Long minPrice,
            @RequestParam(required = false) Long maxPrice,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        return searchCarService.findCarByIntervalPrice(minPrice, maxPrice, pageNumber, pageSize);
    }
}
