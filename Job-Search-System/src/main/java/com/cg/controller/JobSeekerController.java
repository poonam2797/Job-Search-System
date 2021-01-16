package com.cg.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.cg.entity.AppliedJobs;
import com.cg.entity.FavoriteJobs;
import com.cg.entity.Job;
import com.cg.entity.JobSeeker;
import com.cg.exception.JobSeekerException;
import com.cg.service.AddToApplyDO;
import com.cg.service.AppliedJobsDO;
import com.cg.service.FavoriteJobsDO;
import com.cg.service.JobSeekerServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Poonam
 *
 */
@Api
@Slf4j
@RestController
@RequestMapping("/api/jss/jobSeekers")
public class JobSeekerController {

	@Autowired(required=false)
	@Qualifier(value="jobSeekerServiceSpring")
	private JobSeekerServices jobSeekerService;

	/**
	 * Register jobseeker
	 * @param jobSeeker
	 * @return String
	 * @throws JobSeekerException
	 * http://localhost:8081/springfox/api/jss/jobSeekers/
	 */
	@ApiOperation(value= "Register jobseeker", 
			response=String.class,
			consumes="Receives jobseeker object as request body",
			httpMethod="POST"
			)
	@PostMapping("/")
	public String registerJobSeeker(@Valid @RequestBody JobSeeker jobSeeker) throws JobSeekerException {
		try {
			JobSeeker status= jobSeekerService.registerJobSeeker(jobSeeker);
			if(!status.equals(null)) {
				log.info("employer:"+jobSeeker.getJobSeeker_Name()+" added to database");
				return "jobSeeker:"+jobSeeker.getJobSeeker_Name()+" added to database";
			}else {
				log.debug("Unable to register jobSeeker");
				return "Unable to register jobSeeker to database";
			}
		}catch(JobSeekerException e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	/**
	 * Get job by location
	 * @param l1
	 * @return List of jobs
	 * @throws JobSeekerException
	 * http://localhost:8081/springfox/api/jss/jobSeekers/Pune
	 */
	@ApiOperation(value= "Get job by location", 
			response=Job.class,
			tags = "Get jobs",
			consumes = "location",
			httpMethod="GET")
	@GetMapping("/{l1}")
	public ResponseEntity<List<Job>> getJobByLocation(@PathVariable("l1") String l1) throws JobSeekerException{
		try {
			List<Job> productList= jobSeekerService.getJobsByLocation(l1);
			return new ResponseEntity<>(productList,HttpStatus.OK);
		}catch(JobSeekerException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	/**
	 * Get job by id
	 * @param jobId
	 * @return Job
	 * @throws JobSeekerException
	 * http://localhost:8081/springfox/api/jss/jobSeekers/job/21
	 */
	@ApiOperation(value= "Get job by Id", 
			response=Job.class,
			tags = "Get job",
			consumes = "id",
			httpMethod="GET")
	@GetMapping("/job/{jobId}")
	public ResponseEntity<Job> getJobById(@PathVariable Integer jobId) throws JobSeekerException{
		try {
			Job job= jobSeekerService.getJobById(jobId);
			return new ResponseEntity<>(job,HttpStatus.OK);
		}catch(JobSeekerException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	/**
	 * 
	 * @param appliedJobsDO
	 * @return String
	 * @throws JobSeekerException
	 * http://localhost:8081/springfox/api/jss/jobSeekers/applyAJob/
	 */
	@ApiOperation(value= "Apply a job", 
			response=String.class,
			consumes = ("Appiled jobs DAO"),
			httpMethod="POST"
			)
	@PostMapping("/applyAJob/")
	public String applyAJob(@RequestBody AppliedJobsDO appliedJobsDO ) throws JobSeekerException {
		try {
			AppliedJobs status= jobSeekerService.applyAJob(appliedJobsDO);
			if(!status.equals(null)) {
				log.info("job:"+status.getAppliedJob_id()+" added to database");
				return "appliedJob:"+status.getAppliedJob_id()+" added to database";
			}else {
				log.debug("Unable to apply a job in the database");
				return "Unable to apply a job in the database";
			}
		}catch(JobSeekerException e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	/**
	 * Add to fav job
	 * @param favoriteJobsDO
	 * @return String
	 * @throws JobSeekerException
	 * http://localhost:8081/springfox/api/jss/jobSeekers/addToFav
	 */
	@ApiOperation(value= "Add to favourite jobs", 
			response=String.class,
			consumes = "Favourite job DAO",
			httpMethod="POST")	
	@PostMapping("/addToFav")
	public String addToFavJob(@RequestBody FavoriteJobsDO favoriteJobsDO ) throws Exception {
		try {
			FavoriteJobs status= jobSeekerService.addToFavJob(favoriteJobsDO);
			if(!status.equals(null)) {
				System.out.println("job:"+status.getFavJob_id()+" added to database");
				return "FavoriteJob:"+status.getFavJob_id()+" added to database";
			}else {
				System.out.println("Unable to post a job");
				return "Unable to add favorite job in the database";
			}
		}catch(JobSeekerException e) {
			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	/**
	 * Get all fav job
	 * @param jobSeekerId
	 * @return list of fav jobs
	 * @throws JobSeekerException
	 * http://localhost:8081/springfox/api/jss/jobSeekers/getAllFavJobs/20
	 */
	@ApiOperation(value= "Get all favourite jobs", 
			response=FavoriteJobs.class,
			tags = "Get all favourite jobs",
			consumes = "Favourite job id",
			httpMethod="GET")
	@GetMapping("/getAllFavJobs/{jobSeekerId}") 
	public ResponseEntity<List<FavoriteJobs>> getAllFavJob(@PathVariable Integer jobSeekerId) throws JobSeekerException{ 
		try { 
			List<FavoriteJobs> jobList = jobSeekerService.getAllFavJobs(jobSeekerId); 
			return new ResponseEntity<>(jobList,HttpStatus.OK); 
		}catch(JobSeekerException e) { 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage()); 
		} 
	}

	/**
	 * Get all applied jobs
	 * @param jobSeekerId
	 * @return List of applied jobs
	 * http://localhost:8081/springfox/api/jss/jobSeekers/getAppliedJobs/22
	 */
	@ApiOperation(value= "Get all applied jobs", 
			response=AppliedJobs.class,
			tags = "Get all applied jobs",
			consumes = "Applied job id",
			httpMethod="GET")
	@GetMapping("/getAppliedJobs/{appliedJobId}")
	public ResponseEntity<List<AppliedJobs>> getAllAppliedJobs(@PathVariable Integer jobSeekerId){
		try {
			List<AppliedJobs> productList = jobSeekerService.getAllAppliedJobs(jobSeekerId);
			return new ResponseEntity<>(productList,HttpStatus.OK);
		}catch(JobSeekerException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	/**
	 * Delete applied job
	 * @param id
	 * @return String
	 * @throws JobSeekerException
	 * http://localhost:8081/springfox/api/jss/jobSeekers/deleteAppliedJob/id
	 */
	@ApiOperation(value= "Delete applied job", 
			response=String.class,
			tags = "Delete job",
			consumes = "id",
			httpMethod="DELETE"
			)
	@DeleteMapping("/deleteAppliedJob/{id}")
	public String deleteAppliedJob(@PathVariable Integer id) throws JobSeekerException {
		try {
			Integer status= jobSeekerService.deleteAppliedJob(id);
			if(!status.equals(null)) {
				return "job: "+id+" deleted from database";
			}else {

				return "Unable to delete product from database";
			}

		}catch(JobSeekerException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	/**
	 * Delete fav job
	 * @param id
	 * @return String
	 * @throws JobSeekerException
	 * http://localhost:8081/springfox/api/jss/jobSeekers/deleteFavoriteJob/id
	 */
	@ApiOperation(value= "Delete favourite job", 
			response=String.class,
			tags = "Delete job",
			consumes = "id",
			httpMethod="DELETE"
			)
	@DeleteMapping("/deleteFavoriteJob/{id}")
	public String deleteFavoriteJob(@PathVariable Integer id) throws JobSeekerException {
		try {
			Integer status= jobSeekerService.deleteFavoriteJob(id);
			if(!status.equals(null)) {
				return "job: "+id+" deleted from database";
			}else {

				return "Unable to delete product from database";
			}

		}catch(JobSeekerException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	/**
	 * Add to applied job
	 * @param addToApplyDO
	 * @return String
	 * @throws JobSeekerException
	 * http://localhost:8081/springfox/api/jss/jobSeekers/addToApply/
	 */
	@ApiOperation(value= "Add to applied job", 
			response=String.class,
			consumes = "add to apply DAO",
			httpMethod="POST")
	@PostMapping("/addToApply/")
	public String addToAppliedJob(@RequestBody AddToApplyDO addToApplyDO ) throws JobSeekerException {
		try {
			AppliedJobs status= jobSeekerService.addToAppliedJobs(addToApplyDO);
			if(!status.equals(null)) {
				log.info("job:"+addToApplyDO.getFavJobId()+" added to Applied Job");
				return "appliedJob:"+status.getAppliedJob_id()+" added to database";
			}else {
				log.debug("Unable to post a job");
				return "Unable to apply a job in the database";
			}
		}catch(JobSeekerException e) {
		log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
}


