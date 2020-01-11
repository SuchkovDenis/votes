package ru.suchkov.votesystem.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DishDto {
    private String title;
    private BigDecimal cost;
}
