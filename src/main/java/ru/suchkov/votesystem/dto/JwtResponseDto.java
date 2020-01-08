package ru.suchkov.votesystem.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponseDto  {

	private String jwttoken;
}