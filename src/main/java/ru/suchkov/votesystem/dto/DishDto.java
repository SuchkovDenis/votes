package ru.suchkov.votesystem.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DishDto {
    private Long id;
    private String title;
    private double price;
    private LocalDate date;
}
