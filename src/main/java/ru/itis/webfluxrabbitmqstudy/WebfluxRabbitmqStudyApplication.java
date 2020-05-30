package ru.itis.webfluxrabbitmqstudy;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;
import ru.itis.webfluxrabbitmqstudy.entity.Restaurant;
import ru.itis.webfluxrabbitmqstudy.entity.Table;
import ru.itis.webfluxrabbitmqstudy.repository.RestaurantRepository;
import ru.itis.webfluxrabbitmqstudy.repository.TableRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class WebfluxRabbitmqStudyApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(WebfluxRabbitmqStudyApplication.class, args);
        generateData(context);
    }

    private static void generateData(ConfigurableApplicationContext context) {
        RestaurantRepository restaurantRepository = context.getBean(RestaurantRepository.class);
        TableRepository tableRepository = context.getBean(TableRepository.class);

        List<Table> tables = new ArrayList<>();
        tables.add(Table.builder().place(4).isBooked(false).build());
        tables.add(Table.builder().place(2).isBooked(false).build());
        tables.add(Table.builder().place(2).isBooked(false).build());
        tables.add(Table.builder().place(4).isBooked(true).build());
        Restaurant shaurma = restaurantRepository.save(Restaurant.builder()
                .address("Kremlyovskaya 5")
                .email("shaurma@gmail.com")
                .name("Shaurma")
                .tables(tables)
                .build());
        tables.forEach(table -> table.setRestaurant(shaurma));
        tableRepository.saveAll(tables);

        tables.clear();
        tables.add(Table.builder().place(3).isBooked(false).build());
        tables.add(Table.builder().place(2).isBooked(false).build());
        tables.add(Table.builder().place(3).isBooked(true).build());
        tables.add(Table.builder().place(4).isBooked(false).build());
        Restaurant kfc = restaurantRepository.save(Restaurant.builder()
                .address("Kremlyovskaya 15")
                .email("kfc@gmail.com")
                .name("KFC")
                .tables(tables)
                .build());
        tables.forEach(table -> table.setRestaurant(kfc));
        tableRepository.saveAll(tables);


    }

    @Bean
    public RestTemplate restTemplate() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }
}
