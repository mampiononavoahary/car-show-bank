package com.fresh.coding.carshow.services;

import com.fresh.coding.carshow.dtos.responses.BrandWithCarsResponse;

import java.util.List;

public interface BrandWithCarService {
    List<BrandWithCarsResponse> getCarsGroupedByBrand(Integer limit);
}
