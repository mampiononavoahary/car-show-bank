package com.fresh.coding.carshow.controllers;

import com.fresh.coding.carshow.entities.Car;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class CarRestController {

    @GetMapping
    public List<Car> getAllCars(){
        return List.of();
    }
}
