package ru.suchkov.votesystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class VoteResultsDto {
    private List<Long> restaurantIds;
}
