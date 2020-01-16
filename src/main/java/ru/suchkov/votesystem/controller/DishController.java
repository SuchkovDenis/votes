package ru.suchkov.votesystem.controller;

import io.swagger.annotations.Api;
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
@RequestMapping(value = "/dishes", name="Dish resource",
        produces = "application/json", consumes = "application/json")
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

    @PostMapping("/{restaurantId}")
    @Secured(ADMIN)
    public DishDto create(@RequestBody DishDto dishDto, @PathVariable Long restaurantId) {
        Dish dish = dishMapper.fromDto(dishDto);
        dish.setRestaurant(restaurantRepository.getOne(restaurantId));
        dish.setDate(LocalDate.now());
        return dishMapper.toDto(dishRepository.save(dish));
    }

    @DeleteMapping("/{dishId}")
    @Secured(ADMIN)
    public void delete(@PathVariable Long dishId) {
        dishRepository.deleteById(dishId);
    }
}
