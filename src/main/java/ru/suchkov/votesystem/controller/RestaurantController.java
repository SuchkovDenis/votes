package ru.suchkov.votesystem.controller;

import org.springframework.format.annotation.DateTimeFormat;
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

@RestController
@RequestMapping("/restaurants")
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
    public List<RestaurantDto> getAll() {
       return restaurantRepository.findAll().stream().map(restaurantMapper::toDto).collect(Collectors.toList());
    }

    @PostMapping
    public RestaurantDto create(@RequestBody RestaurantDto restaurantDto) {
        return restaurantMapper.toDto(restaurantRepository.save(restaurantMapper.fromDto(restaurantDto)));
    } // TODO create update

    @DeleteMapping("/{restaurantId}")
    public void delete(@PathVariable Long restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }

    @GetMapping("/{restaurantId}/menu/{date}")
    public List<DishDto> getMenu(@PathVariable Long restaurantId,
                                 @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return dishRepository.findAllByRestaurantIdAndAndDate(restaurantId, date).stream().map(dishMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{restaurantId}/menu")
    public List<DishDto> getMenu(@PathVariable Long restaurantId) {
        return getMenu(restaurantId, LocalDate.now());
    }
}
