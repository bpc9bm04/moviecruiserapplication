package com.stackroute.moviecruiserauthentication.service;

import java.util.Map;

import com.stackroute.moviecruiserauthentication.domain.User;

public interface SecurityTokenGenerator {

	Map<String, String> generateToken(User user);
}
