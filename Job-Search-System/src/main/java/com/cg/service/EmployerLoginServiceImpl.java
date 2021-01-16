package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.dao.EmployerLoginDAO;
import com.cg.entity.Employer;
import com.cg.entity.EmployerLogin;
import com.cg.entity.LogOutPayload;
import com.cg.exception.OperationFailedException;
import com.cg.exception.ResourceNotFound;
import java.util.Optional;
/**
 * 
 * @author Sandhya 
 *
 */

@Service
public class EmployerLoginServiceImpl implements EmployerLoginService {

	@Autowired 
	private EmployerLoginDAO loginDAO;

	/**
	 * Sign in
	 * @Param employer
	 * @return String
	 */
	@Override
	public String signIn(EmployerLogin employer) {
		String str = null;
		Optional<Employer> employerObj = loginDAO.findById(employer.getUserId());
		if (!employerObj.isPresent()) {
			System.out.println(employerObj);
			throw new ResourceNotFound("USER_NOT_FOUND");
		} else {
			String pwd = employerObj.get().getPassword();
			if (!pwd.equals(employer.getPassword())) {
				throw new ResourceNotFound("WRONG_PASSWORD");
			}
			try {
				str = "Sign in sucessfull";
				loginDAO.saveAndFlush(employerObj.get());
			} catch (Exception e) {
				throw new OperationFailedException("OPERATION_FAILED");
			}
		}
		return str;
	}

	/**
	 * Sign out
	 * @Param employer
	 * @return String
	 */
	@Override
	public String signOut(LogOutPayload employer) {
		String str = null;
		EmployerLogin el= new EmployerLogin();
		Optional<Employer> employerObj = loginDAO.findById(el.getUserId());
		if (!employerObj.isPresent()) {
			throw new ResourceNotFound("USER_NOT_FOUND");
		} else {
			try {
				str = "Sign Out sucessfull";
				loginDAO.saveAndFlush(employerObj.get());
			} catch (Exception e) {
				throw new OperationFailedException("OPERATION_FAILED");
			}
		}
		return str;
	}

	/**
	 * reset
	 * @Param user
	 * @return String
	 */
	public String changePassword(EmployerLogin user, String new_password) {
		String str = null;
		Optional<Employer> employerObj = loginDAO.findById(user.getUserId());
		if (!employerObj.isPresent()) {
			throw new ResourceNotFound("USER_NOT_FOUND");
		} else {
			String pwd = employerObj.get().getPassword();
			if (!pwd.equals(user.getPassword())) {
				throw new ResourceNotFound("WRONG_PASSWORD");
			}
			try {
				employerObj.get().setPassword(new_password);
				loginDAO.saveAndFlush(employerObj.get());
				str = "Password changed sucessfully";
			} catch (Exception e) {
				throw new OperationFailedException("OPERATION_FAILED");
			}
		}
		return str;
	}
}