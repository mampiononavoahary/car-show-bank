package com.fresh.coding.carshow.controllers;

import com.fresh.coding.carshow.dtos.requests.AppointmentRequest;
import com.fresh.coding.carshow.dtos.responses.AppointmentSummarized;
import com.fresh.coding.carshow.services.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointments")
public class AppointmentRestController {
    private final AppointmentService appointmentService;
    
    @PostMapping
    public AppointmentSummarized createAppointment(@RequestBody @Valid AppointmentRequest appointmentRequest){
        return appointmentService.createAppointment(appointmentRequest);
    }
    
    @GetMapping
    public List<AppointmentSummarized> getAllAppointments(){
        return appointmentService.findAllAppointments();
    }

    @GetMapping("/{id}")
    public AppointmentSummarized getAppointment(@PathVariable Long id){
        return appointmentService.findAppointment(id);
    }

    @PatchMapping("/{id}/{status}")
    public AppointmentSummarized updateStatusAppointment(@PathVariable Long id, @PathVariable String status){
        return appointmentService.updateStatusAppointment(id, status);
    }
}
