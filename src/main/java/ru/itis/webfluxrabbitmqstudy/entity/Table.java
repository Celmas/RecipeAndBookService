package ru.itis.webfluxrabbitmqstudy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@javax.persistence.Table(name = "table_entity")
public class Table {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    private boolean isBooked;
    private int place;

    public void book() {
        if (isBooked) {
            throw new IllegalArgumentException();
        } else {
            setBooked(true);
        }
    }
}
