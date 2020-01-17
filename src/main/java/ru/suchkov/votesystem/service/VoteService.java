package ru.suchkov.votesystem.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.suchkov.votesystem.model.Restaurant;
import ru.suchkov.votesystem.model.User;
import ru.suchkov.votesystem.model.Vote;
import ru.suchkov.votesystem.repository.RestaurantRepository;
import ru.suchkov.votesystem.repository.VoteRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class VoteService {

    private static final LocalTime DEAD_LINE = LocalTime.of(11, 0);

    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;

    public VoteService(VoteRepository voteRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    public boolean vote(Long restaurantId, User user) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if (optionalRestaurant.isPresent()) {
            Optional<Vote> optionalVote = voteRepository.findByUserId(user.getId());
            Restaurant restaurant = optionalRestaurant.get();
            Vote vote;
            if (optionalVote.isEmpty()) {
                vote = new Vote(user, restaurant, LocalDateTime.now());
                voteRepository.save(vote);
                log.info("Make new vote for restaurant id {} from {}", restaurantId, user);
                return true;
            } else {
                vote = optionalVote.get();
                if (LocalDateTime.now().isAfter(LocalDateTime.of(LocalDate.now(), DEAD_LINE))) {
                    log.info("Decision did'not change for user {}", user);
                    return vote.getRestaurant().getId().equals(restaurantId);
                } else {
                    vote.setRestaurant(restaurant);
                    vote.setDate(LocalDateTime.now());
                    voteRepository.save(vote);
                    log.info("Change vote for restaurant id {} from {}", restaurantId, user);
                    return true;
                }
            }
        } else {
            log.info("Invalid restaurant id {}", restaurantId);
            return false;
        }
    }

    public List<Vote> getResults() {
        return voteRepository.findAllByDateAfter();
    }
}
