package com.ecommerce.ECommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ecommerce.ECommerce.exception.EmailFailed;
import com.ecommerce.ECommerce.model.VerificationToken;

@Service
public class EmailService {

    @Value("{app.frontend.url}")
    private String url;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${email.from}")
    private String fromAddress;

    private SimpleMailMessage makesimpleMailMessage() {
        SimpleMailMessage simpleMailMessage2 = new SimpleMailMessage();
        simpleMailMessage2.setFrom(fromAddress);

        return simpleMailMessage2;
    }

    public void sendVerificationEmail(VerificationToken verification_token) throws EmailFailed {
        SimpleMailMessage message = makesimpleMailMessage();
        message.setTo(verification_token.getUser().getEmail());
        message.setSubject("Email Verification to account");
        message.setText("Please click on the link below to verify your account: \n " + url + "/verify?token="
                + verification_token.getToken());

        try {
            javaMailSender.send(message);
        } catch (MailException e) {
            // TODO: handle exception
            e.printStackTrace();
            throw new EmailFailed("Failed to send email");
        }

    }

}
