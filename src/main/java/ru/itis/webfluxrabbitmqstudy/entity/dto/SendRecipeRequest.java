package ru.itis.webfluxrabbitmqstudy.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendRecipeRequest {
    private String email;
    private String query;
}
