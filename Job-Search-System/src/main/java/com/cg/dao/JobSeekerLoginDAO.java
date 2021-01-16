package com.cg.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.entity.Employer;
import com.cg.entity.JobSeeker;
/**
* This JobSeekerLoginDAO class represents the repository that extends JPA repository
* @author admin
*
*/
@Repository
public interface JobSeekerLoginDAO extends JpaRepository<JobSeeker, Integer>{

}
