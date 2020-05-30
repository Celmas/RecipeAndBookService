package ru.itis.webfluxrabbitmqstudy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Restaurant {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String address;
    private String email;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Table> tables;

}
