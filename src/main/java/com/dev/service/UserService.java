package com.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.model.User;
import com.dev.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username);
	}
	
	public void create(User u) {
		u.setUserPassword(passwordEncoder.encode(u.getUserPassword()));
		userRepository.save(u);
	}
}
