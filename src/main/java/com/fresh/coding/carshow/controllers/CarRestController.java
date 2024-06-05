package com.fresh.coding.carshow.controllers;

import com.fresh.coding.carshow.dtos.requests.CarRequest;
import com.fresh.coding.carshow.dtos.responses.CarSummarized;
import com.fresh.coding.carshow.dtos.responses.CarWithImageSummarized;
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
    public List<CarSummarized> createAllCars(
            @RequestBody
            List<@Valid CarRequest> carRequests) {
        return carService.createAllCars(carRequests);
    }

    @PutMapping("/{id}")
    public CarSummarized updateCar(@PathVariable Long id, @RequestBody @Valid CarRequest carRequest) {
        return carService.modifyCarById(id, carRequest);
    }

    @DeleteMapping("/{id}")
    public CarSummarized deleteCar(@PathVariable Long id) {
        return carService.deleteCarById(id);
    }

    @GetMapping
    public Paginate<List<CarWithImageSummarized>> getCarWithImages(
            @RequestParam(defaultValue = "0") String page,
            @RequestParam(defaultValue = "10") String perPage
    ) {
        return carService.paginateCars(Integer.valueOf(page), Integer.valueOf(perPage));
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

    @GetMapping("/type/{type}/exclude/{id}")
    public List<CarWithImageSummarized> getCarsByTypeAndExcludeId(
            @PathVariable String type,
            @PathVariable Long id) {
        return carService.findCarsByTypeAndExcludeId(type, id);
    }

}
