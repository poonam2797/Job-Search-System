package com.cg.service;

import com.cg.entity.Message;
import com.cg.exception.MessageException;
/**
 * 
 * @author wintech
 *
 */
public interface MessageService {
	
	public Message sendMessageToEmployer(MessageDO messageDO) throws MessageException;

	Message sendMessageToJobSeeker(MessageDO messageDO) throws MessageException;
	
}
