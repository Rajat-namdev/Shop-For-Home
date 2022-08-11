package com.c2_g4_sfh_main.serviceInterface;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.c2_g4_sfh_main.entity.User;

public interface IUserService {
	User findOne(String email);

	Collection<User> findByRole(String role);

	User save(User user);

	User update(User user);

	List<User> findAll();

	Object update(Long userId);

	User update(String email);

	Page<User> findAll(PageRequest request);

	User removeAdmin(String email);

	void removeUser(String email);
}
