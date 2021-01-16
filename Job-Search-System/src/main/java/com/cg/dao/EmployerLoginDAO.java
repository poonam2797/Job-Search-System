package com.cg.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.entity.Employer;
/**
* This EmployerLoginDAO class represents the repository that extends JPA repository
* @author admin
*
*/
@Repository
public interface EmployerLoginDAO extends JpaRepository<Employer, Integer>{

}
