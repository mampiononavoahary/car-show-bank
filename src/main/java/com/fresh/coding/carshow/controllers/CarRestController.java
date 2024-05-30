package com.fresh.coding.carshow.controllers;

import com.fresh.coding.carshow.dtos.requests.CarRequest;
import com.fresh.coding.carshow.dtos.responses.CarResponse;
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

    @GetMapping("/type")
    public List<String> getByCarType() {
        return carService.findAllByCarType();
    }

    @GetMapping("/motor-type")
    public List<String> getByMotorType() {
        return carService.findAllByMotorType();
    }


    @GetMapping("/pinned")
    public List<CarResponse> getPinnedCars(
            @RequestParam(defaultValue = "6") String limit
    ) {
        return carService.findPinnedCars(Integer.valueOf(limit));
    }


    @GetMapping("/{id}")
    public CarResponse getCar(@PathVariable Long id) {
        return carService.findById(id);
    }

}
