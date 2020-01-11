package ru.suchkov.votesystem.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.suchkov.votesystem.model.Role;
import ru.suchkov.votesystem.model.User;

import java.util.Collections;

@Service
@Slf4j
public class UserService implements UserDetailsService {

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("user".equals(username)) {
			log.info("load by userName");
			// login: user
			// password: password
			User user = new User();
			user.setId(1L);
			user.setUsername("user");
			user.setPassword("$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6");
			Role role = new Role();
			role.setName("ROLE_ADMIN");
			user.setRoles(Collections.singleton(role));
			return user;
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}