package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.EmployerLogin;
import com.cg.entity.LogOutPayload;
import com.cg.exception.BaseResponse;
import com.cg.service.EmployerLoginService;


//	import com.capgemini.advertisement.entity.LogOutPayload;
//	import com.capgemini.advertisement.entity.Login;
//	import com.capgemini.advertisement.exception.BaseResponse;
//	import com.capgemini.advertisement.service.LoginService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author Sandhya
 *
 */

@RestController
@RequestMapping("/api/jobsearchsystemlogins")
@Api(value = "Login")
public class EmployerLoginController {
	@Autowired 
	private EmployerLoginService loginService;

	/**
	 * Sign in
	 * @param user
	 * @return Response entity
	 * http://localhost:8081/api/jobsearchsystemlogins/employerlogin
	 */
	@PostMapping("/employerlogin") 
	@ApiOperation(value = "SignIn")
	public ResponseEntity<?> signIn( @RequestBody EmployerLogin user) {
		String str = loginService.signIn(user);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(str);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	/**
	 * Sign out
	 * @param user
	 * @return Response entity
	 * http://localhost:8081/api/jobsearchsystemlogins/logout
	 */
	@PostMapping("/logout") 
	@ApiOperation(value = "SignOut")
	public ResponseEntity<?> signOut( @RequestBody LogOutPayload user) {
		String str = loginService.signOut(user);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(str);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	/**
	 * Reset password
	 * @param user
	 * @param new_password
	 * @return Response entity
	 * http://localhost:8081/api/jobsearchsystemlogins/reset/new_password
	 */
	@PostMapping("/reset/{new_password}")
	@ApiOperation(value = "Reset Password")
	public ResponseEntity<?> changePassword( @RequestBody EmployerLogin user,@PathVariable String new_password) {
		String str =loginService.changePassword(user, new_password);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(str);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}
}


