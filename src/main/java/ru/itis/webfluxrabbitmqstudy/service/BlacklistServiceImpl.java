package ru.itis.webfluxrabbitmqstudy.service;

import org.springframework.stereotype.Service;
import ru.itis.webfluxrabbitmqstudy.entity.BlacklistPerson;
import ru.itis.webfluxrabbitmqstudy.entity.dto.BlacklistPersonDto;
import ru.itis.webfluxrabbitmqstudy.repository.BlacklistPersonRepository;

import java.util.Optional;

@Service
public class BlacklistServiceImpl implements BlacklistService {
    private BlacklistPersonRepository repository;

    public BlacklistServiceImpl(BlacklistPersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<BlacklistPerson> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public void addBlacklistPerson(BlacklistPersonDto dto) {
        BlacklistPerson person = BlacklistPerson.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .build();
        repository.save(person);
    }
}
