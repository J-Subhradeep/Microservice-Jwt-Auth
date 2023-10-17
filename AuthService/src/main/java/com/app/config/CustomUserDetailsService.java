package com.app.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.entities.UserCredential;
import com.app.repository.UserCredentialRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserCredentialRepository repository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<UserCredential> credentials = repository.findByEmail(username);
		
		if(credentials.isPresent()) {
			UserCredential userCredential = credentials.get();
			return new CustomUserDetails(userCredential);
		}
		else {
			throw new UsernameNotFoundException("Not Found:"+username);
		}
		
		
		
	}

}
