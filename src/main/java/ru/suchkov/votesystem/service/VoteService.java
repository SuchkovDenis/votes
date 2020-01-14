package ru.suchkov.votesystem.service;

import org.springframework.stereotype.Service;
import ru.suchkov.votesystem.repository.VoteRepository;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }
}
