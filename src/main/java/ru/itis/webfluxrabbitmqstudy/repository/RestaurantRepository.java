package ru.itis.webfluxrabbitmqstudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.webfluxrabbitmqstudy.entity.Restaurant;


public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
