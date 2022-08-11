package com.wipro.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.springboot.entity.User;

import java.util.Collection;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {
	User findByEmail(String email);

	Collection<User> findAllByRole(String role);

}
