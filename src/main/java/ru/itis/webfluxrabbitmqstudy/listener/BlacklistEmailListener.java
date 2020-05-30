package ru.itis.webfluxrabbitmqstudy.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import ru.itis.webfluxrabbitmqstudy.entity.dto.EmailMessage;

@Component
public class BlacklistEmailListener {
    private JavaMailSender emailSender;

    public BlacklistEmailListener(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @RabbitListener(queues = "email.blacklist.q")
    public void receiveMessage(EmailMessage emailMessage) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailMessage.getEmail());
        message.setFrom("BlacklistListener@dev.com");
        message.setSubject("Blacklist Notification");
        message.setText(emailMessage.getText());
        emailSender.send(message);
    }
}
