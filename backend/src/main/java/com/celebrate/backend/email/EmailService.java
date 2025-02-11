package com.celebrate.backend.email;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

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

    public String sendEmailWithAttachement(EmailWithAttachementDto email){

        if (email.getTo() == null || email.getTo().isBlank()) {
            return "Erro: Endereço de e-mail do destinatário está vazio.";
        }

        try{

            byte[] pdfBytes = Base64.getDecoder().decode(email.getPdfBase64());

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getMessage());

            ByteArrayResource pdfAnexo = new ByteArrayResource(pdfBytes);
            helper.addAttachment("contrato.pdf", pdfAnexo);

            mailSender.send(message);

            return "Email Enviado";

        } catch (IllegalArgumentException e) {

            return "Erro: Base64 inválido.";

        } catch (MessagingException e) {

            return "Erro ao configurar o e-mail.";

        } catch(Exception e){

            e.printStackTrace();
            return "Erro ao tentar enviar o email: " + e.getLocalizedMessage();
        }
    }
}