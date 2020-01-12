package ru.suchkov.votesystem.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.suchkov.votesystem.dto.DishDto;
import ru.suchkov.votesystem.mapper.DishMapper;
import ru.suchkov.votesystem.model.Dish;
import ru.suchkov.votesystem.model.User;

import java.math.BigDecimal;

@RestController
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
	public String helloAll(@AuthenticationPrincipal User user) {
		System.out.println(user);
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
