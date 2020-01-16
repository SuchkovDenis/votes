package ru.suchkov.votesystem.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
@ApiModel(value = "Vote Results", description = "Results of vote")
public class VoteResultsDto {
    @ApiModelProperty(value = "results", example = "" +
            "{\n" +
            "    1: 12,\n" +
            "    2: 1,\n" +
            "    3: 7\n" +
            "}")
    private Map<Long, Long> results;
}
