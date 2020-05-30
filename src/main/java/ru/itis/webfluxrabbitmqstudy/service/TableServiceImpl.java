package ru.itis.webfluxrabbitmqstudy.service;

import org.springframework.stereotype.Service;
import ru.itis.webfluxrabbitmqstudy.entity.Table;
import ru.itis.webfluxrabbitmqstudy.repository.TableRepository;

import java.util.Optional;

@Service
public class TableServiceImpl implements TableService {
    private TableRepository repository;

    public TableServiceImpl(TableRepository repository) {
        this.repository = repository;
    }

    @Override
    public Table book(Long tableId) {
        Optional<Table> table = repository.findById(tableId);
        if (table.isPresent()) {
            Table candidate = table.get();
            candidate.book();
            repository.save(candidate);
            return candidate;
        } else {
            throw new IllegalArgumentException("there is no table");
        }
    }
}
