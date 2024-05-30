package com.fresh.coding.carshow.controllers;

import com.fresh.coding.carshow.dtos.responses.ImageResponse;
import com.fresh.coding.carshow.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageRestController {
    private final ImageService imageService;

    @PostMapping
    public ImageResponse createImage(
            @RequestParam String carId,
            @RequestParam MultipartFile file
    ) {
        return imageService.createImage(Long.valueOf(carId), file);
    }
}
