package ru.itis.webfluxrabbitmqstudy.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlacklistPersonDto {
    private String name;
    private String surname;
    private String email;
}
