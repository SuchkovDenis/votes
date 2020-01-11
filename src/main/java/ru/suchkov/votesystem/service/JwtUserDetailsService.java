package ru.suchkov.votesystem.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.suchkov.votesystem.CurrentUser;
import ru.suchkov.votesystem.model.Role;
import ru.suchkov.votesystem.model.User;

import java.util.Collections;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("user".equals(username)) {
			log.info("load by name");
			// login: user
			// password: password
			User user = new User();
			user.setId(1L);
			user.setName("user");
			user.setPassword("$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6");
			Role role = new Role();
			role.setName("ROLE_ADMIN");
			user.setRoles(Collections.singleton(role));
			return new CurrentUser(user);
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}