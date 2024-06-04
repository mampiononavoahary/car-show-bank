package com.fresh.coding.carshow.controllers;

import com.fresh.coding.carshow.dtos.responses.ImageSummarized;
import com.fresh.coding.carshow.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageRestController {
    private final ImageService imageService;

    @PostMapping
    public ImageSummarized createImage(
            @RequestParam String carId,
            @RequestParam MultipartFile file
    ) {
        return imageService.createImage(Long.valueOf(carId), file);
    }

    @GetMapping
    public List<ImageSummarized> getAllImages() {
        return imageService.findAllImages();
    }

    @GetMapping("/{carId}")
    public List<ImageSummarized> getImage(@PathVariable Long carId) {
        return imageService.findImage(carId);
    }

    @PutMapping
    public ImageSummarized createImage(
            @RequestParam String carId,
            @RequestParam String id,
            @RequestParam MultipartFile file
    ) {
        return imageService.updateImage(Long.valueOf(id), Long.valueOf(carId), file);
    }

    @DeleteMapping("/{carId}")
    public List<ImageSummarized> deleteImage(@PathVariable Long carId) {
        return imageService.deleteImage( carId);
    }
}
