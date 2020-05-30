package ru.itis.webfluxrabbitmqstudy.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.webfluxrabbitmqstudy.entity.dto.EmailMessage;

@Service
public class RecipeEmailServiceImpl implements EmailService {
    @Value("${rabbitmq.email-recipe.routingkey}")
    private String emailHoroscopeRoutingKey;
    @Value("${rabbitmq.email.exchange}")
    private String emailExchange;

    private RabbitTemplate rabbitTemplate;

    private final String RECIPE_TEXT = "We glad to share recipes with you\n";

    public RecipeEmailServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public boolean sendEmail(String email, String body) {
        EmailMessage emailMessage = EmailMessage.builder()
                .email(email)
                .text(RECIPE_TEXT.concat(body))
                .build();
        rabbitTemplate.convertAndSend(emailExchange, emailHoroscopeRoutingKey, emailMessage);
        return true;
    }
}
