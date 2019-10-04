package com.stackroute.moviecruiserauthentication.service;

import com.stackroute.moviecruiserauthentication.domain.User;
import com.stackroute.moviecruiserauthentication.exception.UserExistException;
import com.stackroute.moviecruiserauthentication.exception.UserNotFoundException;

public interface UserService {

	boolean saveUser(User user) throws UserExistException;
	User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;
}
