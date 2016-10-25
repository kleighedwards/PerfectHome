package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import data.UserDAO;
import security.JsonWebTokenGenerator;

@RestController
public class AuthenticationController {

	@Autowired
	JsonWebTokenGenerator jwtGen;
	
	@Autowired
	UserDAO userDAO;
}
