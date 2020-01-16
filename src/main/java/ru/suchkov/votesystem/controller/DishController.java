package ru.suchkov.votesystem.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.suchkov.votesystem.dto.DishDto;
import ru.suchkov.votesystem.mapper.DishMapper;
import ru.suchkov.votesystem.model.Dish;
import ru.suchkov.votesystem.repository.DishRepository;
import ru.suchkov.votesystem.repository.RestaurantRepository;

import java.time.LocalDate;

import static ru.suchkov.votesystem.util.Roles.ADMIN;

@Api(tags="Dishes")
@RestController
@RequestMapping(value = "/dishes", name="Dish resource", produces = MediaType.APPLICATION_JSON_VALUE)
public class DishController {

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;
    private final DishMapper dishMapper;

    public DishController(DishRepository dishRepository, RestaurantRepository restaurantRepository,
                          DishMapper dishMapper) {
        this.dishRepository = dishRepository;
        this.restaurantRepository = restaurantRepository;
        this.dishMapper = dishMapper;
    }

    @PostMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Secured(ADMIN)
    @ApiOperation(value = "Create or update dish", notes = "Create or update dish in certain restaurant",
            response = DishDto.class, authorizations = {@Authorization(value = "Bearer")})
    public DishDto create(@RequestBody DishDto dishDto,
                          @ApiParam(value = "Id value of restaurant", required = true)
                          @PathVariable Long restaurantId) {
        Dish dish = dishMapper.fromDto(dishDto);
        dish.setRestaurant(restaurantRepository.getOne(restaurantId));
        dish.setDate(LocalDate.now());
        return dishMapper.toDto(dishRepository.save(dish));
    }

    @DeleteMapping("/{dishId}")
    @Secured(ADMIN)
    @ApiOperation(value = "Delete dish", notes = "Delete dish by id",
            authorizations = {@Authorization(value = "Bearer")})
    public void delete(@ApiParam(value = "Id value of dish", required = true)
                           @PathVariable Long dishId) {
        dishRepository.deleteById(dishId);
    }
}
