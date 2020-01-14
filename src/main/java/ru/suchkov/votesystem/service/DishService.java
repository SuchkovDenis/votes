package ru.suchkov.votesystem.service;

import org.springframework.stereotype.Service;
import ru.suchkov.votesystem.repository.VoteRepository;

@Service
public class DishService {

    private final VoteRepository voteRepository;

    public DishService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }
}
