package com.stackroute.moviecruiserauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.moviecruiserauthentication.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	/*
	 * @Query("Select u from User u where userId = (?1) and password = (?2)") User
	 * validate(String userId, String password);
	 */

	User findByUserIdAndPassword(String userId, String password);
}
