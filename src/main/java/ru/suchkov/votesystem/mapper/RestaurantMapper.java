package ru.suchkov.votesystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.suchkov.votesystem.dto.RestaurantDto;
import ru.suchkov.votesystem.model.Restaurant;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    @Mapping(source = "name", target = "title")
    RestaurantDto toDto(Restaurant dish);

    @Mapping(source = "title", target = "name")
    Restaurant fromDto(RestaurantDto dishDto);
}
