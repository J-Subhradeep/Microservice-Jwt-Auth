package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.entities.UserCredential;
import java.util.List;
import java.util.Optional;


public interface UserCredentialRepository extends JpaRepository<UserCredential,Integer> {
	Optional<UserCredential> findByName(String name);
	Optional<UserCredential> findByEmail(String email);
}
