package ru.suchkov.votesystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.suchkov.votesystem.dto.DishDto;
import ru.suchkov.votesystem.model.Dish;

@Mapper(componentModel = "spring")
public interface DishMapper {

    @Mapping(source = "name", target = "title")
    DishDto dishToDishDto(Dish dish);

    @Mapping(source = "title", target = "name")
    Dish dishDtoToDish(DishDto dishDto);
}
