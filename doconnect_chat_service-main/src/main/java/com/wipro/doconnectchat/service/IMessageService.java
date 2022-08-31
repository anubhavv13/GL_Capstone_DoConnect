package com.wipro.doconnectchat.service;
/*
* @Author : Sourabh
* Modified last date: 30-08-22
* Description :
*/
import java.util.List;

import javax.validation.Valid;

import com.wipro.doconnectchat.dto.MessageDTO;

public interface IMessageService {

	public MessageDTO sendMessage(@Valid MessageDTO messageDTO);

	public List<MessageDTO> getMessage();

}
