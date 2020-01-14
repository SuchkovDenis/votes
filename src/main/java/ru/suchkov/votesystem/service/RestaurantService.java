package ru.suchkov.votesystem.service;

import org.springframework.stereotype.Service;
import ru.suchkov.votesystem.repository.RestaurantRepository;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }
}
