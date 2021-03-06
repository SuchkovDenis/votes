package ru.suchkov.votesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.suchkov.votesystem.model.Vote;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("FROM Vote v WHERE v.user.id = ?1 AND v.date >= current_date")
    Optional<Vote> findByUserId(Long userId);

    @Query("FROM Vote v WHERE v.date >= current_date")
    List<Vote> findAllByDateAfter();
}
