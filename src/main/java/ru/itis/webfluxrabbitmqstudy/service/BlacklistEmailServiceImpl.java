package ru.itis.webfluxrabbitmqstudy.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.webfluxrabbitmqstudy.entity.BlacklistPerson;
import ru.itis.webfluxrabbitmqstudy.entity.dto.EmailMessage;
import ru.itis.webfluxrabbitmqstudy.repository.BlacklistPersonRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class BlacklistEmailServiceImpl implements EmailService {
    private RabbitTemplate rabbitTemplate;
    private BlacklistPersonRepository repository;

    @Value("${rabbitmq.email.exchange}")
    private String emailExchange;
    @Value("${rabbitmq.email-blacklist.routingkey}")
    private String emailBlacklistRoutingKey;

    private final String BLACKLIST_TEXT = "We found wanted person making request: %s";

    public BlacklistEmailServiceImpl(RabbitTemplate rabbitTemplate, BlacklistPersonRepository repository) {
        this.rabbitTemplate = rabbitTemplate;
        this.repository = repository;
    }

    @Override
    public boolean sendEmail(String email, String body) {
        String[] blackListPerson = body.split(" ");
        repository.save(BlacklistPerson.builder()
                .name(blackListPerson[0])
                .surname(blackListPerson[1])
                .lastActivity(Timestamp.valueOf(LocalDateTime.now()))
                .build());
        EmailMessage emailMessage = EmailMessage.builder()
                .email(email)
                .text(String.format(BLACKLIST_TEXT, body))
                .build();
        rabbitTemplate.convertAndSend(emailExchange, emailBlacklistRoutingKey, emailMessage);
        return true;
    }
}
