package com.fresh.coding.carshow.dtos.responses;

import java.util.List;

public record BrandWithCarsResponse(
        String brand,
        List<CarResponse> cars
) {}
