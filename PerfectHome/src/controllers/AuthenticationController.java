package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.UserDAO;
import entities.User;
import security.JsonWebTokenGenerator;

@RestController
public class AuthenticationController {

	@Autowired
	JsonWebTokenGenerator jwtGen;

	@Autowired
	UserDAO userDAO;

	// Login
	@RequestMapping(value = "/auth/login", method = RequestMethod.POST)
	public Map<String, String> login(HttpServletRequest req, HttpServletResponse res,
			@RequestBody String userJsonString) {

		ObjectMapper mapper = new ObjectMapper();
		User user = null;

		try {
			user = mapper.readValue(userJsonString, User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			user = userDAO.authenticateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String jws = jwtGen.generateUserJwt(user);
		Map<String, String> responseJson = new HashMap<>();
		responseJson.put("jwt", jws);
		return responseJson;
	}
	
	// Check for existing user account
	@RequestMapping(value = "/auth/account", method = RequestMethod.POST)
	public User hasAccount(HttpServletRequest req, HttpServletResponse res,
			@RequestBody String userJSON) {
		System.out.println("call to DAO: /auth/account");
		ObjectMapper mapper = new ObjectMapper();
		User user = null;
		
		try {
			
			user = mapper.readValue(userJSON, User.class);
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		user = userDAO.hasAccount(user);
		
		if(user == null){
			res.setStatus(200);
		}
		else {
			res.setStatus(400);
		}
		
		System.out.println("Checking this user for a previous account: " + user);
		return user;
	}

	// Sign-up
	@RequestMapping(value = "/auth/signup", method = RequestMethod.POST)
	public Map<String, String> signup(HttpServletRequest req, HttpServletResponse res, @RequestBody String userJson) {

		ObjectMapper mapper = new ObjectMapper();
		User user = null;

		try {
			user = mapper.readValue(userJson, User.class);
		} catch (IOException ie) {
			ie.printStackTrace();
		}

		user = userDAO.create(user);
		String jws = jwtGen.generateUserJwt(user);
		Map<String, String> responseJson = new HashMap<>();
		responseJson.put("jwt", jws);

		res.setStatus(201);

		return responseJson;
	}
}
