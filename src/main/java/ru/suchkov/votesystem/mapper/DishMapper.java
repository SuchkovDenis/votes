package ru.suchkov.votesystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.suchkov.votesystem.dto.DishDto;
import ru.suchkov.votesystem.model.Dish;

@Mapper(componentModel = "spring")
public interface DishMapper {

    @Mapping(source = "name", target = "title")
    DishDto toDto(Dish dish);

    @Mapping(target = "restaurant", ignore = true)
    @Mapping(source = "title", target = "name")
    Dish fromDto(DishDto dishDto);
}
