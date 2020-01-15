package ru.suchkov.votesystem.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.suchkov.votesystem.model.User;
import ru.suchkov.votesystem.service.VoteService;

import java.util.List;
import java.util.stream.Collectors;

import static ru.suchkov.votesystem.util.Roles.USER;

@RestController
@RequestMapping("/votes")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/{restaurantId}")
    @Secured(USER)
    public boolean vote(@PathVariable Long restaurantId, @AuthenticationPrincipal User user) {
        return voteService.vote(restaurantId, user);
    }

    @GetMapping
    public List<Long> getResults()  {
        return voteService.getResults().stream().map(vote -> vote.getRestaurant().getId()).collect(Collectors.toList());
    }
}
