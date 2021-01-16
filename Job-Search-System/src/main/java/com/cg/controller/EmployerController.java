package com.cg.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.cg.entity.Employer;
import com.cg.entity.Job;
import com.cg.entity.JobSeeker;
import com.cg.exception.EmployerException;
import com.cg.service.EmployerService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author wintech
 *
 */
@Slf4j
@Api
@RestController
@RequestMapping("/api/jss/employers")
public class EmployerController {
	@Autowired
	private EmployerService employerService;

	/**
	 * 
	 * @param employer
	 * @return String
	 * @throws Exception
	 * http://localhost:8081/springfox/api/jss/employers/
	 */
	@PostMapping("/")
	public String registerEmployer(@Valid @RequestBody Employer employer) throws Exception {
		try {
			Employer status= employerService.registerEmployer(employer);
			if(!status.equals(null)) {
				log.info("employer:"+employer.getOrganizationName()+" added to database");
				return "employer:"+employer.getOrganizationName()+" added to database";
			}else {
				log.debug("Unable to register employer");
				return "Unable to register employer to database";
			}

		}catch(EmployerException e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	/**
	 * 
	 * @param job
	 * @param id
	 * @return String
	 * @throws Exception
	 * http://localhost:8081/springfox/api/jss/employers/postAJob/1
	 */
	@PostMapping("/postAJob/{id}")
	public String postAJob(@RequestBody Job job, @PathVariable Integer id) throws EmployerException {
		try {
			Job status= employerService.postAjob(job,id);
			if(!status.equals(null)) {
				log.info("job:"+job.getTitle()+" added to database");
				return "job:"+job.getTitle()+" added to database";
			}else {
				log.debug("Unable to post a job");
				return "Unable to post job in the database";
			}
		}catch(EmployerException e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	/**
	 * Get job by id
	 * @param jobId
	 * @return job
	 * http://localhost:8081/springfox/api/jss/employers/3
	 */
	@GetMapping("/{jobId}")
	public ResponseEntity<Job> getJobById(@PathVariable Integer jobId){
		try {
			Job job= employerService.getJobById(jobId);
			return new ResponseEntity<>(job,HttpStatus.OK);
		}catch(EmployerException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	/**
	 * delete job
	 * @param Id
	 * @return Response entity job
	 * http://localhost:8081/springfox/api/jss/employers/3
	 */
	@DeleteMapping("/delete/{id}")
	public String deleteJob(@PathVariable Integer id) throws EmployerException{
		try {
			Integer status= employerService.deleteJob(id);
			if(!status.equals(null)) {
				return "job: "+id+" deleted from database";
			}else {
				return "Unable to delete product from database";
			}
		}catch(EmployerException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	/**
	 * Get all jobs
	 * @param Id
	 * @return list of jobs
	 * http://localhost:8081/springfox/api/jss/employer/getAllJobs/2
	 */
	@GetMapping("/getAllJobs/{id}")
	public ResponseEntity<List<Job>> getAllJob(@PathVariable Integer id) throws EmployerException{
		try {
			List<Job> jobList = employerService.getAllJobs(id);
			return new ResponseEntity<>(jobList,HttpStatus.OK);
		}catch(EmployerException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param skill
	 * @return list of jobseeker
	 * @throws EmployerException
	 * http://localhost:8081/springfox/api/jss/employers/skill/Java
	 */
	@GetMapping("/skill/{skill}")
	public ResponseEntity<List<JobSeeker>> getJobSeekersBySkill(@PathVariable("skill") String skill) throws EmployerException{
		try {
			List<JobSeeker> jobSeekerList= employerService.searchJobSeekersBySkillSets(skill);
			return new ResponseEntity<>(jobSeekerList,HttpStatus.OK);
		}catch(EmployerException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	/**
	 * Update job
	 * @param job
	 * @return Response entity job
	 * http://localhost:8081/springfox/api/jss/employers/editAJob
	 */
	@PutMapping("/editAJob")
	public ResponseEntity<Job> updateJob(@RequestBody Job job) {
		try {
			Job updatedJob= employerService.editAJob(job);
			return new ResponseEntity<>(updatedJob,HttpStatus.OK);

		}catch(EmployerException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
}