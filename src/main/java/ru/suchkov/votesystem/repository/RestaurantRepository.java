package ru.suchkov.votesystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.suchkov.votesystem.model.Restaurant;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
}
