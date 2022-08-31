package com.wipro.doconnectchat.service;
/*
* @Author : Anubhav,Sourabh
* Modified last date: 30-08-22
* Description: 
* Params: 
* Return TYpe: 
* Exception: 
*/
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.doconnectchat.dto.MessageDTO;
import com.wipro.doconnectchat.entity.Message;
import com.wipro.doconnectchat.repository.IMessageRepo;

@Service
public class IMessageServiceImpl implements IMessageService {

	@Autowired
	private IMessageRepo messageRepo;

	/*
	* @Author :Anubhav
	* Modified last date: 30-08-22
	* Description: 
	* Params: 
	* Return TYpe: 
	* Exception: 
	*/
	@Override
	public MessageDTO sendMessage(@Valid MessageDTO messageDTO) {

		Message message = new Message();
		message.setMessage(messageDTO.getMessage());
		message.setFromUser(messageDTO.getFromUser());
		message = messageRepo.save(message);

		messageDTO.setFromUser(message.getFromUser());
		messageDTO.setMessage(message.getMessage());

		return messageDTO;
	}
	/*
	* @Author : Anubhav
	* Modified last date: 30-08-22
	* Description: 
	* Params: 
	* Return TYpe: 
	* Exception: 
	*/

	@Override
	public List<MessageDTO> getMessage() {
		List<MessageDTO> data = new ArrayList<MessageDTO>();

		List<Message> messages = messageRepo.findAll();
		for (Message message : messages) {

			MessageDTO messageDTO = new MessageDTO();
			messageDTO.setFromUser(message.getFromUser());
			messageDTO.setMessage(message.getMessage());
			data.add(messageDTO);

		}

		return data;
	}

}
