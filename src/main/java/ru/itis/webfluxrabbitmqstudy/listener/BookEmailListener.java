package ru.itis.webfluxrabbitmqstudy.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import ru.itis.webfluxrabbitmqstudy.entity.dto.EmailMessage;

@Component
public class BookEmailListener {
    private JavaMailSender emailSender;

    public BookEmailListener(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @RabbitListener(queues = "email.book.q")
    public void receiveMessage(EmailMessage emailMessage) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailMessage.getEmail());
        message.setFrom("Reservation@dev.com");
        message.setSubject("Booking Notification");
        message.setText(emailMessage.getText());
        emailSender.send(message);
    }
}
