package com.fresh.coding.carshow.dtos.responses;

import com.fresh.coding.carshow.enums.CarStatus;

public record CarResponse(
        Long id,
        String name,
        String description,
        String brand,
        String model,
        Long price,
        String color,
        String motorType,
        String type,
        Integer power,
        String placeNumber,
        CarStatus status
) {
}
