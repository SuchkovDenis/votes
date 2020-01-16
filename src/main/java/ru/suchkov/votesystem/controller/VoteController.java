package ru.suchkov.votesystem.controller;

import io.swagger.annotations.Api;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.suchkov.votesystem.dto.VoteResultsDto;
import ru.suchkov.votesystem.model.User;
import ru.suchkov.votesystem.service.VoteService;

import java.util.HashMap;
import java.util.Map;

import static ru.suchkov.votesystem.util.Roles.USER;

@Api(tags="Votes")
@RestController
@RequestMapping(value = "/votes", name="Vote resource",
        produces = "application/json", consumes = "application/json")
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
    public VoteResultsDto getResults()  {
        Map<Long, Long> map = new HashMap<>();
        voteService.getResults().forEach(vote -> {
            long id = vote.getRestaurant().getId();
            if (map.containsKey(id)) {
                map.put(id, map.get(id) + 1);
            } else {
                map.put(id, 1L);
            }
        });
        return new VoteResultsDto(map);
    }
}
