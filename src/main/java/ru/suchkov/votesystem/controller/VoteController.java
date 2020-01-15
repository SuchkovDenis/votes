package ru.suchkov.votesystem.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.suchkov.votesystem.model.User;
import ru.suchkov.votesystem.service.VoteService;

import static ru.suchkov.votesystem.util.Roles.USER;

@RestController
@RequestMapping("/vote")
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
}
