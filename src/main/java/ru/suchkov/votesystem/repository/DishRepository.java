package ru.suchkov.votesystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.suchkov.votesystem.model.Dish;

@Repository
public interface DishRepository extends CrudRepository<Dish, Long> {
}
