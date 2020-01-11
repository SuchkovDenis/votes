package ru.suchkov.votesystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.suchkov.votesystem.CurrentUser;
import ru.suchkov.votesystem.dto.DishDto;
import ru.suchkov.votesystem.mapper.DishMapper;
import ru.suchkov.votesystem.model.Dish;

import java.math.BigDecimal;

@RestController
@CrossOrigin
public class HelloWorldController {

	final DishMapper dishMapper;

    public HelloWorldController(DishMapper dishMapper) {
        this.dishMapper = dishMapper;
    }

    @GetMapping("/hello")
	public String hello() {
		return "Hello World";
	}

	@GetMapping("/helloAll")
	public String helloAll(@AuthenticationPrincipal CurrentUser user) {
		return "Hello All";
	}

	@GetMapping("/helloAdmin")
	public DishDto helloAdmin() {
		Dish dish = new Dish();
		dish.setName("Окорок");
		dish.setPrice(new BigDecimal(12));
		return dishMapper.dishToDishDto(dish);
	}

	@GetMapping("/helloUser")
	public String helloUser() {
		return "Hello User";
	}
}
