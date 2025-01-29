package com.celebrate.backend.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public String sendEmail(EmailDto email){

        if (email.getTo() == null || email.getTo().isBlank()) {
            return "Erro: Endereço de e-mail do destinatário está vazio.";
        }

        try{

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            System.out.println("Enviando para: " + email.getTo());

            simpleMailMessage.setTo(email.getTo());
            simpleMailMessage.setSubject(email.getSubject());
            simpleMailMessage.setText(email.getMessage());

            mailSender.send(simpleMailMessage);

            return "Email Enviado";
        } catch(Exception e){

            e.printStackTrace();
            return "Erro ao tentar enviar o email: " + e.getLocalizedMessage();
        }
    }
}
