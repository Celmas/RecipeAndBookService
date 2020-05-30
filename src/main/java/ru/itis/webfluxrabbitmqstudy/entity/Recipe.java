package ru.itis.webfluxrabbitmqstudy.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recipe {
    private String title;
    private String href;
    private String ingredients;
    private String thumbnail;
}
