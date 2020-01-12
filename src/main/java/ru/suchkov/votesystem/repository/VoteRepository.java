package ru.suchkov.votesystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.suchkov.votesystem.model.Vote;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {
}
