package com.fresh.coding.carshow.controllers;

import com.fresh.coding.carshow.dtos.requests.CarRequest;
import com.fresh.coding.carshow.dtos.responses.CarSummarized;
import com.fresh.coding.carshow.dtos.responses.CarWithImageSummarized;
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
    public List<CarSummarized> createAllCars(
            @RequestBody
            List<@Valid CarRequest> carRequests) {
        return carService.createAllCars(carRequests);
    }

    @PutMapping("/{id}")
    public CarSummarized updateCar(@PathVariable Long id, @RequestBody @Valid CarRequest carRequest){
        return carService.modifyCarById(id, carRequest);
    }

    @DeleteMapping("/{id}")
    public CarSummarized deleteCar(@PathVariable Long id){
        return carService.deleteCarById(id);
    }

    @GetMapping
    public List<CarWithImageSummarized> getCarWithImages() {
        return carService.findAllCars();
    }

    @GetMapping("/brand")
    public List<String> getAllBrandOfCars(
            @RequestParam(defaultValue = "6") String limit
    ) {
        return carService.findAllBrandOfCars(Integer.valueOf(limit));
    }

    @GetMapping("/type-motor")
    public List<String> getAllMotorTypeOfCars() {
        return carService.findAllMotorTypeOfCars();
    }

    @GetMapping("/type")
    public List<String> getAllTypeOfCars() {
        return carService.findAllTypeOfCars();
    }


    @GetMapping("/pinned")
    public List<CarSummarized> getAllCarByStatusPinned(
            @RequestParam(defaultValue = "6") String limit
    ) {
        return carService.findAllCarByStatusPinned(Integer.valueOf(limit));
    }

}
