package ru.suchkov.votesystem.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
@ApiModel(value = "Dish", description = "Dish in a restaurant")
public class DishDto {

    @ApiModelProperty(value = "Id", example = "777")
    private Long id;

    @ApiModelProperty(value = "Title", example = "Very tasty dish", required = true)
    private String title;

    @ApiModelProperty(value = "Price", example = "15.4", required = true)
    private double price;

    @ApiModelProperty(value = "Date", example = "2020-01-15")
    private LocalDate date;
}
