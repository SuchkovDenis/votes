package ru.suchkov.votesystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.suchkov.votesystem.dto.DishDto;
import ru.suchkov.votesystem.model.Dish;

@Mapper(componentModel = "spring")
public interface DishMapper {

    @Mapping(source = "name", target = "title")
    @Mapping(source = "price", target = "cost")
    DishDto dishToDishDto(Dish dish);
}
