package ru.suchkov.votesystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.suchkov.votesystem.dto.DishDto;
import ru.suchkov.votesystem.mapper.DishMapper;
import ru.suchkov.votesystem.model.Dish;

import java.math.BigDecimal;

// todo пониать что за пользователь сейчас
@RestController
@CrossOrigin()
public class HelloWorldController {

	@Autowired
	DishMapper dishMapper;

	@RequestMapping({ "/hello" })
	public String hello() {
		return "Hello World";
	}

	@RequestMapping({ "/helloAdmin" })
	public DishDto helloAdmin() {
		Dish dish = new Dish();
		dish.setName("Окорок");
		dish.setPrice(new BigDecimal(12));
		return dishMapper.dishToDishDto(dish);
	}

	@RequestMapping({ "/helloUser" })
	public String helloUser() {
		return "Hello User";
	}
}
