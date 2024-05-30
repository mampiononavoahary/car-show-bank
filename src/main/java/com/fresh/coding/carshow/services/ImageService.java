package com.fresh.coding.carshow.services;

import com.fresh.coding.carshow.dtos.responses.ImageResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    ImageResponse createImage(Long carId, MultipartFile file);
}
