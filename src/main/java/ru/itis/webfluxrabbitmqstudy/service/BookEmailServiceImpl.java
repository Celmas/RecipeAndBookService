package ru.itis.webfluxrabbitmqstudy.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.webfluxrabbitmqstudy.entity.dto.EmailMessage;

@Service
public class BookEmailServiceImpl implements EmailService {
    @Value("${rabbitmq.email-book.routingkey}")
    private String emailBookRoutingKey;
    @Value("${rabbitmq.email.exchange}")
    private String emailExchange;

    private RabbitTemplate rabbitTemplate;

    private final String BOOK_TEXT = "Your table #%s has been booked";

    public BookEmailServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public boolean sendEmail(String email, String body) {
        EmailMessage emailMessage = EmailMessage.builder()
                .email(email)
                .text(String.format(BOOK_TEXT, body))
                .build();
        rabbitTemplate.convertAndSend(emailExchange, emailBookRoutingKey, emailMessage);
        return true;
    }
}
