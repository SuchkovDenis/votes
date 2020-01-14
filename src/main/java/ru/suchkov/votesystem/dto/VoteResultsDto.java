package ru.suchkov.votesystem.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
public class VoteResultsDto {
    private LocalDate date;
    private Map<String, Integer> results;
}
