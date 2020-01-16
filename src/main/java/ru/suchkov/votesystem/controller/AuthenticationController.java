package ru.suchkov.votesystem.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.suchkov.votesystem.service.UserService;
import ru.suchkov.votesystem.util.JwtTokenUtil;
import ru.suchkov.votesystem.dto.JwtRequestDto;
import ru.suchkov.votesystem.dto.JwtResponseDto;

import java.util.Objects;

@Api(tags="Authentication")
@RestController
@RequestMapping(name = "Authentication token provider", produces = MediaType.APPLICATION_JSON_VALUE,
		consumes = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AuthenticationController {

	private final AuthenticationManager authenticationManager;

	private final JwtTokenUtil jwtTokenUtil;

	private final UserService userService;

	public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
									UserService userService) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
		this.userService = userService;
	}

	@PostMapping("/authenticate")
	@ApiOperation(value = "Authenticate in a system.",
			notes = "Provide an authentication token. After successfully login you should use special header" +
			"in all your requests: \"Authorization: Bearer <token>\"",
			response = JwtResponseDto.class)
	public ResponseEntity<JwtResponseDto> createAuthenticationToken(@RequestBody JwtRequestDto authenticationRequest) {
		try {
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		} catch (NullPointerException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (BadCredentialsException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (DisabledException e) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		UserDetails user = userService.loadUserByUsername(authenticationRequest.getUsername());
		String token = jwtTokenUtil.generateToken(user);
		log.info("Authenticate user with roles: {}",user.getAuthorities());
		return ResponseEntity.ok(new JwtResponseDto(token));
	}

	private void authenticate(String username, String password) throws NullPointerException  {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}
}
