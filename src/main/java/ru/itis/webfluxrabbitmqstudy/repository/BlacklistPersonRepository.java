package ru.itis.webfluxrabbitmqstudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.webfluxrabbitmqstudy.entity.BlacklistPerson;

import java.util.Optional;

public interface BlacklistPersonRepository extends JpaRepository<BlacklistPerson, Long> {
    Optional<BlacklistPerson> findByEmail(String email);
}
