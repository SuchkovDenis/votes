package ru.suchkov.votesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.suchkov.votesystem.model.Dish;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

    @Query("FROM Dish d WHERE d.date = :date and d.restaurant.id = :restaurantId")
    List<Dish> findAllByRestaurantIdAndAndDate(Long restaurantId, LocalDate date);
}
