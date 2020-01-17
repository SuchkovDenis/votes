package ru.suchkov.votesystem.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.suchkov.votesystem.dto.DishDto;
import ru.suchkov.votesystem.dto.RestaurantDto;
import ru.suchkov.votesystem.mapper.DishMapper;
import ru.suchkov.votesystem.mapper.RestaurantMapper;
import ru.suchkov.votesystem.model.Dish;
import ru.suchkov.votesystem.repository.DishRepository;
import ru.suchkov.votesystem.repository.RestaurantRepository;

import java.time.LocalDate;

@Api(tags="Admin")
@RestController
@RequestMapping(value = "/admin", name="Admin resource", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;
    private final DishMapper dishMapper;
    private final RestaurantMapper restaurantMapper;

    public AdminController(DishRepository dishRepository, RestaurantRepository restaurantRepository,
                           DishMapper dishMapper, RestaurantMapper restaurantMapper) {
        this.dishRepository = dishRepository;
        this.restaurantRepository = restaurantRepository;
        this.dishMapper = dishMapper;
        this.restaurantMapper = restaurantMapper;
    }

    @PostMapping(value = "/restaurants", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create or update restaurant", notes = "Create or update restaurant",
            response = RestaurantDto.class, authorizations = {@Authorization(value = "Basic")})
    public RestaurantDto create(@RequestBody RestaurantDto restaurantDto) {
        return restaurantMapper.toDto(restaurantRepository.save(restaurantMapper.fromDto(restaurantDto)));
    }

    @PostMapping(value = "/dishes/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create or update dish", notes = "Create or update dish in certain restaurant",
            response = DishDto.class, authorizations = {@Authorization(value = "Basic")})
    public DishDto create(@RequestBody DishDto dishDto,
                          @ApiParam(value = "Id value of restaurant", required = true, defaultValue = "1")
                          @PathVariable Long restaurantId) {
        Dish dish = dishMapper.fromDto(dishDto);
        dish.setRestaurant(restaurantRepository.getOne(restaurantId));
        dish.setDate(LocalDate.now());
        return dishMapper.toDto(dishRepository.save(dish));
    }

    @DeleteMapping("/dishes/{dishId}")
    @ApiOperation(value = "Delete dish", notes = "Delete dish by id",
            authorizations = {@Authorization(value = "Basic")})
    public void delete(@ApiParam(value = "Id value of dish", required = true, defaultValue = "1")
                           @PathVariable Long dishId) {
        dishRepository.deleteById(dishId);
    }
}
