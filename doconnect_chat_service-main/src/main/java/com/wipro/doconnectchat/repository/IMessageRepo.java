package com.wipro.doconnectchat.repository;
/*
* @Author : Anubhav
* Modified last date: 30-08-22
* Description :
*/
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.doconnectchat.entity.Message;

@Repository
public interface IMessageRepo extends JpaRepository<Message, Long> {

}
