package ru.itis.webfluxrabbitmqstudy.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RMQConfig {
    @Value("${rabbitmq.email.exchange}")
    private String emailExchange;

    @Value("${rabbitmq.email-recipe.queue}")
    private String emailHoroscopeQueueName;

    @Value("${rabbitmq.email-recipe.routingkey}")
    private String emailHoroscopeRoutingKey;

    @Value("${rabbitmq.email-blacklist.queue}")
    private String emailBlackQueueName;

    @Value("${rabbitmq.email-blacklist.routingkey}")
    private String emailBlacklistRoutingKey;

    @Value("${rabbitmq.email-book.queue}")
    private String emailBookQueueName;

    @Value("${rabbitmq.email-book.routingkey}")
    private String emailBookRoutingKey;

    @Bean
    Queue queue1() {
        return new Queue(emailHoroscopeQueueName, true);
    }

    @Bean
    Queue queue2() {
        return new Queue(emailBlackQueueName, true);
    }

    @Bean
    Queue queue3() {
        return new Queue(emailBookQueueName, true);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(emailExchange);
    }

    @Bean
    Binding binding1(Queue queue1, DirectExchange exchange) {
        return BindingBuilder.bind(queue1).to(exchange).with(emailHoroscopeRoutingKey);
    }

    @Bean
    Binding binding2(Queue queue2, DirectExchange exchange) {
        return BindingBuilder.bind(queue2).to(exchange).with(emailBlacklistRoutingKey);
    }

    @Bean
    Binding binding3(Queue queue3, DirectExchange exchange) {
        return BindingBuilder.bind(queue3).to(exchange).with(emailBookRoutingKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
