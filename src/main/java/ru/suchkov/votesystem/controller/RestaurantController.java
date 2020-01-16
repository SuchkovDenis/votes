package ru.suchkov.votesystem.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.suchkov.votesystem.dto.DishDto;
import ru.suchkov.votesystem.dto.RestaurantDto;
import ru.suchkov.votesystem.mapper.DishMapper;
import ru.suchkov.votesystem.mapper.RestaurantMapper;
import ru.suchkov.votesystem.repository.DishRepository;
import ru.suchkov.votesystem.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static ru.suchkov.votesystem.util.Roles.ADMIN;

@Api(tags="Restaurants", authorizations = {@Authorization(value = "Bearer")})
@RestController
@RequestMapping(value = "/restaurants", name="Restaurant resource", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;
    private final RestaurantMapper restaurantMapper;
    private final DishMapper dishMapper;

    public RestaurantController(RestaurantRepository restaurantRepository, DishRepository dishRepository,
                                RestaurantMapper restaurantMapper, DishMapper dishMapper) {
        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;
        this.restaurantMapper = restaurantMapper;
        this.dishMapper = dishMapper;
    }

    @GetMapping
    @ApiOperation(value = "Get all restaurants", notes = "Get list of all existing restaurants",
            responseContainer = "List", response = RestaurantDto.class,
            authorizations = {@Authorization(value = "Bearer")})
    public List<RestaurantDto> getAll() {
       return restaurantRepository.findAll().stream().map(restaurantMapper::toDto).collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Secured(ADMIN)
    @ApiOperation(value = "Create or update restaurant", notes = "Create or update restaurant",
            response = RestaurantDto.class, authorizations = {@Authorization(value = "Bearer")})
    public RestaurantDto create(@RequestBody RestaurantDto restaurantDto) {
        return restaurantMapper.toDto(restaurantRepository.save(restaurantMapper.fromDto(restaurantDto)));
    }

    @GetMapping("/{restaurantId}/menu/{date}")
    @ApiOperation(value = "Menu of restaurant", notes = "Get menu of restaurant for some day",
            responseContainer = "List", response = DishDto.class, authorizations = {@Authorization(value = "Bearer")})
    public List<DishDto> getMenu(@ApiParam(value = "Id value of restaurant", required = true)
                                     @PathVariable Long restaurantId,
                                 @ApiParam(value = "data in ISO format")
                                 @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return dishRepository.findAllByRestaurantIdAndAndDate(restaurantId, date).stream().map(dishMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{restaurantId}/menu")
    @ApiOperation(value = "Today's menu of restaurant", notes = "Get today's menu of restaurant",
            responseContainer = "List", response = DishDto.class, authorizations = {@Authorization(value = "Bearer")})
    public List<DishDto> getMenu(@ApiParam(value = "Id value of restaurant", required = true)
                                     @PathVariable Long restaurantId) {
        return getMenu(restaurantId, LocalDate.now());
    }
}
