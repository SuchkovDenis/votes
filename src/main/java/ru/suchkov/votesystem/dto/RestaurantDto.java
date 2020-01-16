package ru.suchkov.votesystem.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Restaurant", description = "Restaurant")
public class RestaurantDto {

    @ApiModelProperty(value = "Id", example = "777")
    private Long id;

    @ApiModelProperty(value = "title", example = "McDonald's", required = true)
    private String title;
}
