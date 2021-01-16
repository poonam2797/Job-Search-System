package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.entity.JobSeekerLogin;
import com.cg.entity.LogOutPayload;
import com.cg.exception.BaseResponse;
import com.cg.service.JobSeekerLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/jobsearchsystemlogin")
@Api(value = "Login")
public class JobSeekerLoginController {

	@Autowired 
	private JobSeekerLoginService loginService;

	/**
	 * Sign in
	 * @param user
	 * @return Response entity
	 * http://localhost:8081/api/jobsearchsystemlogin/login
	 */
	@PostMapping("/login") 
	@ApiOperation(value = "SignIn")
	public ResponseEntity<?> signIn( @RequestBody JobSeekerLogin user) {
		String str = loginService.signIn(user);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(str);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	/**
	 * Sign logout
	 * @param user
	 * @return
	 * http://localhost:8081/api/jobsearchsystemlogin/logout
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
	 * http://localhost:8081/api/jobsearchsystemlogin/reset/new_password
	 */
	@PostMapping("/reset/{new_password}")
	@ApiOperation(value = "Reset Password")
	public ResponseEntity<?> changePassword( @RequestBody JobSeekerLogin user,@PathVariable String new_password) {
		String str =loginService.changePassword(user, new_password);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(str);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}
}


