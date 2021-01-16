package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.dao.JobSeekerLoginDAO;
import com.cg.entity.Employer;
import com.cg.entity.JobSeeker;
import com.cg.entity.JobSeekerLogin;
import com.cg.entity.LogOutPayload;
import com.cg.exception.OperationFailedException;
import com.cg.exception.ResourceNotFound;
import java.util.Optional;
/**
 * 
 * @author Poonam
 *
 */

@Service
public class JobSeekerLoginServiceImpl implements JobSeekerLoginService {

	@Autowired 
	private JobSeekerLoginDAO loginDAO;

	/**
	 * Sign in
	 * @Param user
	 * @return String
	 */
	@Override
	public String signIn(JobSeekerLogin user) {
		String str = null;
		Optional<JobSeeker> jobSeekerObj = loginDAO.findById(user.getUserId());
		if (!jobSeekerObj.isPresent()) {
			System.out.println(jobSeekerObj);
			throw new ResourceNotFound("USER_NOT_FOUND");
		} else {
			String pwd = jobSeekerObj.get().getPassword();
			if (!pwd.equals(user.getPassword())) {
				throw new ResourceNotFound("WRONG_PASSWORD");
			}
			try {
				str = "Sign in sucessfull";
				loginDAO.saveAndFlush(jobSeekerObj.get());
			} catch (Exception e) {
				throw new OperationFailedException("OPERATION_FAILED");
			}
		}
		return str;
	}

	/**
	 * Sign out
	 * @Param user
	 * @return String
	 */
	@Override
	public String signOut(LogOutPayload jobSeeker) {
		String str = null;
		Optional<JobSeeker> jobSeekerObj = loginDAO.findById(1);
		if (!jobSeekerObj.isPresent()) {
			throw new ResourceNotFound("USER_NOT_FOUND");
		} else {
			try {
				str = "Sign Out sucessfull";
				loginDAO.saveAndFlush(jobSeekerObj.get());
			} catch (Exception e) {
				throw new OperationFailedException("OPERATION_FAILED");
			}
		}
		return str;
	}

	/**
	 * Reset password
	 * @Param user
	 * @return String
	 */
	@Override
	public String changePassword(JobSeekerLogin user, String new_password) {
		String str = null;
		Optional<JobSeeker> jobSeekerObj = loginDAO.findById(user.getUserId());
		if (!jobSeekerObj.isPresent()) {
			throw new ResourceNotFound("USER_NOT_FOUND");
		} else {
			String pwd = jobSeekerObj.get().getPassword();
			if (!pwd.equals(user.getPassword())) {
				throw new ResourceNotFound("WRONG_PASSWORD");
			}
			try {
				jobSeekerObj.get().setPassword(new_password);
				loginDAO.saveAndFlush(jobSeekerObj.get());
				str = "Password changed sucessfully";
			} catch (Exception e) {
				throw new OperationFailedException("OPERATION_FAILED");
			}
		}
		return str;
	}
}