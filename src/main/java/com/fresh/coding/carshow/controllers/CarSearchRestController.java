package com.fresh.coding.carshow.controllers;

import com.fresh.coding.carshow.dtos.responses.CarWithImageSummarized;
import com.fresh.coding.carshow.services.CarSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarSearchRestController {
    private final CarSearchService carSearchService;

    @GetMapping("/search")
    public List<CarWithImageSummarized> getCarWithImagesByCritical(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String typeMotor,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String minPrice,
            @RequestParam(required = false) String maxPrice
    ) {
        return carSearchService.findCarWithImagesByCritical(
                brand,
                model,
                typeMotor,
                type,
                Long.valueOf(minPrice),
                Long.valueOf(maxPrice)
        );
    }
}
