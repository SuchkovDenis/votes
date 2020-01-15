package ru.suchkov.votesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class VoteResultsDto {
    private Map<Long, Long> results;
}
