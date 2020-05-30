package ru.itis.webfluxrabbitmqstudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.webfluxrabbitmqstudy.entity.Table;

public interface TableRepository extends JpaRepository<Table, Long> {
}
