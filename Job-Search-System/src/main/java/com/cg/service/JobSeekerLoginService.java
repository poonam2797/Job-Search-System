package com.cg.service;

import com.cg.entity.EmployerLogin;
import com.cg.entity.JobSeekerLogin;
import com.cg.entity.LogOutPayload;

public interface JobSeekerLoginService {
	   /**
	    * Sign in customer
	    * @param customerMaster
	    * @return sign in successful
	    * else throw invalid customer
	    */
	    public String signIn(JobSeekerLogin user);

	    /**
	     * Sign out 
	     * @param customerMaster
	     * @return sign out successful
	     */

	    public String signOut(LogOutPayload user);
	    
	    /**
	     * Change Password
	     * @param customerMaster
	     * @param new_password
	     * @return changed password
	     */

	    public String changePassword(JobSeekerLogin user, String new_password);


	}