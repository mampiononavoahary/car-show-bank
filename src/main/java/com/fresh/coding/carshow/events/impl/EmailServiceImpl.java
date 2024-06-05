package com.fresh.coding.carshow.events.impl;

import com.fresh.coding.carshow.events.EmailService;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
public class EmailServiceImpl implements EmailService {

    private final String fromEmail;

    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender, @Value("${spring.mail.username}") String fromEmail) {
        this.javaMailSender = javaMailSender;
        this.fromEmail = fromEmail;
    }


    @Override
    public String sendEmail(MultipartFile[] files, String to, String[] cc, String subject, String body) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(to);
            if (cc != null) {
                mimeMessageHelper.setCc(cc);
            }
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);

            if (files != null) {
                for (MultipartFile file : files) {
                    mimeMessageHelper.addAttachment(
                            Objects.requireNonNull(file.getOriginalFilename()),
                            new ByteArrayDataSource(file.getBytes(), file.getContentType())
                    );
                }
            }
            javaMailSender.send(mimeMessage);
            return "Email envoyé avec succès";
        } catch (Exception e) {
            throw new RuntimeException("Échec de l'envoi de l'email : " + e.getMessage());
        }
    }
}
