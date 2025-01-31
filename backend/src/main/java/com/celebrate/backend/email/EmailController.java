package com.celebrate.backend.email;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/email")
public class EmailController {
    
    private final EmailService emailService;

    @PostMapping
    public void sendMail(@RequestBody EmailDto email){

        emailService.sendEmail(email);
    }
}
