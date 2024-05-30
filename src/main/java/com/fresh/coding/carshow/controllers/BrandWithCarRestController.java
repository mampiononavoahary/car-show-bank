package com.fresh.coding.carshow.controllers;

import com.fresh.coding.carshow.dtos.responses.BrandWithCarsResponse;
import com.fresh.coding.carshow.services.BrandWithCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class BrandWithCarRestController {
    private final BrandWithCarService brandWithCarService;

    @GetMapping("/grouped-by-brand")
    public List<BrandWithCarsResponse> getCarsGroupedByBrand(@RequestParam(defaultValue = "6") Integer limit) {
        return brandWithCarService.getCarsGroupedByBrand(limit);
    }
}
