package com.fresh.coding.carshow.controllers;

import com.fresh.coding.carshow.events.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class EmailSendRestController {

    private final EmailService emailService;

    @PostMapping("/send")
    public String sendEmail(
            @RequestParam(value = "file", required = false)MultipartFile[] file,
            String to,
            String[] cc,
            String subject,
            String body){
        return emailService.sendEmail(file, to, cc, subject, body);

    }

}
