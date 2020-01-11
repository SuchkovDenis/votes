package ru.suchkov.votesystem.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "JWT Request",
		description = "Should contains user's login and password")
public class JwtRequestDto {

	@ApiModelProperty(value = "Login")
	private String username;

	@ApiModelProperty(value = "Password")
	private String password;
}