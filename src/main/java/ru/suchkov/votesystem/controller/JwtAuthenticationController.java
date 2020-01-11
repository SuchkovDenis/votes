package ru.suchkov.votesystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import ru.suchkov.votesystem.util.JwtTokenUtil;
import ru.suchkov.votesystem.dto.JwtRequestDto;
import ru.suchkov.votesystem.dto.JwtResponseDto;

import java.util.Objects;

@RestController
@CrossOrigin
@Slf4j
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Qualifier("userService")
	@Autowired
	private UserDetailsService userDetailsService;

	@PostMapping("/authenticate")
	public ResponseEntity<JwtResponseDto> createAuthenticationToken(@RequestBody JwtRequestDto authenticationRequest)
			throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String token = jwtTokenUtil.generateToken(userDetails);
		log.info("Authenticate user with roles: {}",userDetails.getAuthorities());
		return ResponseEntity.ok(new JwtResponseDto(token));
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
