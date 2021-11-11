package io.javaminds.application.configuration;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.javaminds.application.model.User;
import io.javaminds.application.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findUserByEmail(email);
		try {
			user.orElseThrow(() -> new UserPrincipalNotFoundException("User not found."));
		} catch (UserPrincipalNotFoundException e) {
			e.printStackTrace();
		}
		return user.map(CustomUserDetails::new).get();
	}

}
