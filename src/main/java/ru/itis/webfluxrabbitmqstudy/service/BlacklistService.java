package ru.itis.webfluxrabbitmqstudy.service;

import ru.itis.webfluxrabbitmqstudy.entity.BlacklistPerson;
import ru.itis.webfluxrabbitmqstudy.entity.dto.BlacklistPersonDto;

import java.util.Optional;

public interface BlacklistService {
    Optional<BlacklistPerson> findByEmail(String email);

    void addBlacklistPerson(BlacklistPersonDto personDto);
}
