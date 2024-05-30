package com.fresh.coding.carshow.services.impl;

import com.fresh.coding.carshow.dtos.responses.ImageResponse;
import com.fresh.coding.carshow.entities.Image;
import com.fresh.coding.carshow.exceptions.NotFoundException;
import com.fresh.coding.carshow.files.FileService;
import com.fresh.coding.carshow.mappers.ImageMapper;
import com.fresh.coding.carshow.repositories.CarRepository;
import com.fresh.coding.carshow.repositories.ImageRepository;
import com.fresh.coding.carshow.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final FileService fileService;
    private final ImageMapper imageMapper;
    private final CarRepository carRepository;


    @Transactional
    @Override
    public ImageResponse createImage(Long carId, MultipartFile file) {
        var car = carRepository.findById(carId).orElseThrow(() -> new NotFoundException(String.format("Car with id %d not found", carId)));
        var url = fileService.saveFile(file);
        var image = Image.builder().url(url).build();
        var imageSaved = imageRepository.save(image);
        car.addImage(imageSaved);
        return imageMapper.toResponse(imageSaved);
    }

}
