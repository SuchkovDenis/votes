package ru.suchkov.votesystem.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.suchkov.votesystem.dto.VoteResultsDto;
import ru.suchkov.votesystem.model.User;
import ru.suchkov.votesystem.service.VoteService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

import static ru.suchkov.votesystem.util.Roles.USER;

@Api(tags="Votes")
@RestController
@RequestMapping(value = "/votes", name="Vote resource",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/{restaurantId}")
    @Secured(USER)
    @ApiOperation(value = "Vote for restaurant", notes = "Vote for some restaurant",
            response = Boolean.class, authorizations = {@Authorization(value = "Basic")})
    public boolean vote(@ApiParam(value = "Id value of restaurant", required = true, defaultValue = "1")
                            @PathVariable Long restaurantId,
                        @ApiIgnore @AuthenticationPrincipal User user) {
        return voteService.vote(restaurantId, user);
    }

    @GetMapping
    @ApiOperation(value = "Get results", notes = "Get results of voting",
            response = VoteResultsDto.class, authorizations = {@Authorization(value = "Basic")})
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
