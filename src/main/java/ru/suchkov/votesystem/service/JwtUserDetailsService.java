package ru.suchkov.votesystem.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("user".equals(username)) {
			// login: user
			// password: password
			return new User("user", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
					Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

	// TODO метод, который по User из бд или username может найти список его ролей
	// private Set<SimpleGrantedAuthority> getAuthority(User user)
	// этот метод подставится в заглушку или

}