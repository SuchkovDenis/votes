package ru.suchkov.votesystem.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(value = "JWT Response", description = "Contains authentication token")
public class JwtResponseDto  {

	@ApiModelProperty(value = "Authentication token", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbl9hbmRfdXNsI")
	private String jwtToken;
}