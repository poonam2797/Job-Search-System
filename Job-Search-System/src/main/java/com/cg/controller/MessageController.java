package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.cg.entity.Message;
import com.cg.exception.EmployerException;
import com.cg.exception.JobSeekerException;
import com.cg.exception.MessageException;
import com.cg.service.MessageDO;
import com.cg.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Neelam
 *
 */
@Api
@Slf4j
@RestController
@RequestMapping("/api/jss/message")
public class MessageController {
	
	@Autowired(required=false)
	@Qualifier(value="messageService")
	private MessageService messageService;
	
	/**
	 * Send message to employer
	 * @param messageDO
	 * @return String
	 * @throws EmployerException
	 * http://localhost:8081/springfox/api/jss/message/sendMsgToEmployer
	 */
	@ApiOperation(value= "Send message to employer", 
			response=String.class,
			consumes = "MessageDO",
			httpMethod="POST")	
	@PostMapping("/sendMsgToEmployer")
	public String sendMessageToEmployer(@RequestBody MessageDO messageDO ) throws EmployerException {
		try {
			Message status= messageService.sendMessageToEmployer(messageDO);
			if(!status.equals(null)) {
				log.info("job:"+status.getDescription()+" added to message ");
				return "appliedJob:"+status.getDescription()+" added to database";
			}else {
				log.debug("Unable to post a job");
				return "Unable to apply a job in the database";
			}
		}catch(MessageException e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	
	
	public String sendMessageToJobSeeker(@RequestBody MessageDO messageDO ) throws JobSeekerException {
		try {
			Message status= messageService.sendMessageToEmployer(messageDO);
			if(!status.equals(null)) {
				log.info("Message:"+status.getDescription()+" added to message ");
				return "Message:"+status.getDescription()+" added to database";
			}else {
				log.debug("Unable to post a job");
				return "Unable to apply a job in the database";
			}
		}catch(MessageException e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

}
