package com.fresh.coding.carshow.mappers;

import com.fresh.coding.carshow.dtos.responses.ImageResponse;
import com.fresh.coding.carshow.entities.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {
    public ImageResponse toResponse(Image image) {
        return new ImageResponse(image.getId(), image.getUrl());
    }
}
