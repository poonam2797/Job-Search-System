package com.cg.service;

import com.cg.entity.EmployerLogin;
import com.cg.entity.LogOutPayload;

public interface EmployerLoginService {
   /**
    * Sign in customer
    * @param customerMaster
    * @return sign in successful
    * else throw invalid customer
    */
    public String signIn(EmployerLogin user);

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

    public String changePassword(EmployerLogin user, String new_password);

	


}