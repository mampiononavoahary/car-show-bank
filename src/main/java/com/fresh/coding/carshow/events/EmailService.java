package com.fresh.coding.carshow.events;

import org.springframework.web.multipart.MultipartFile;

public interface EmailService {
    String sendEmail(MultipartFile[] file, String to, String[] cc, String subject, String body);
}
