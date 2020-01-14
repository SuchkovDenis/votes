package ru.suchkov.votesystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.suchkov.votesystem.model.Restaurant;

import java.util.List;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

    List<Restaurant> findAll();
}
