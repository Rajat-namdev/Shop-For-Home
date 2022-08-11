package com.c2_g4_sfh_main.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c2_g4_sfh_main.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {
	User findByEmail(String email);

	Collection<User> findAllByRole(String role);

}
