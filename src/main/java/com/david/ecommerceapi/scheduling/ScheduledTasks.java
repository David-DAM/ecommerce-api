package com.david.ecommerceapi.scheduling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduledTasks {

    private final JavaMailSender mailSender;
    @Value("${spring.mail.personal.username}")
    private String personalEmail;
    @Value("${spring.mail.username}")
    private String senderEmail;

    @Scheduled(fixedRate = 30000)
    public void sendReportOrders() {

        try {

            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(personalEmail);
            message.setFrom(senderEmail);
            message.setSubject("Order report");
            message.setText("Here are the number of orders");
            mailSender.send(message);

        }catch (MailException e){
            log.error("Email Order report could not be send");
        }

        log.info("Email report send correctly");
    }

}
