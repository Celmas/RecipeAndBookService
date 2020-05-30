package ru.itis.webfluxrabbitmqstudy.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import ru.itis.webfluxrabbitmqstudy.entity.dto.EmailMessage;

@Component
public class RecipeEmailListener {
    private JavaMailSender emailSender;

    public RecipeEmailListener(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @RabbitListener(queues = "email.recipe.q")
    public void receiveMessage(EmailMessage emailMessage) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailMessage.getEmail());
        message.setFrom("RecipeListener@dev.com");
        message.setSubject("Recipes");
        message.setText(emailMessage.getText());
        emailSender.send(message);
    }
}
