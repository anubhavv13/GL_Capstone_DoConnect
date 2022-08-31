package com.wipro.doconnect.repository;

/*
* @Author : Adarsh
* Modified last date: 30-08-22
* Description :Interface of UserRepository
*/
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.doconnect.entity.User;

@Repository
public interface IUserRepo extends JpaRepository<User, Long> {

	public User findByEmail(String email);
}
