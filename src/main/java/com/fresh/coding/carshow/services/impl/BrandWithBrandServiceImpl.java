package com.fresh.coding.carshow.services.impl;

import com.fresh.coding.carshow.dtos.responses.BrandWithCarsResponse;
import com.fresh.coding.carshow.mappers.CarMapper;
import com.fresh.coding.carshow.repositories.CarRepository;
import com.fresh.coding.carshow.services.BrandWithCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandWithBrandServiceImpl implements BrandWithCarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public List<BrandWithCarsResponse> getCarsGroupedByBrand(Integer limit) {
        var pageable = PageRequest.of(0, limit);
        var brands = carRepository.findDistinctBrands(pageable);
        var response = new ArrayList<BrandWithCarsResponse>();

        for (var brand : brands) {
            var cars = carRepository.findByBrand(brand, pageable);
            var carResponses = cars.stream()
                    .map(carMapper::toResponse)
                    .collect(Collectors.toList());
            response.add(new BrandWithCarsResponse(brand, carResponses));
        }
        return response;
    }

}
