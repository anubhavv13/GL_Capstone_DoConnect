package com.wipro.doconnect.repository;

/*
* @Author : Adarsh
* Modified last date: 30-08-22
* Description :Interface of AdminRepository
*/

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.doconnect.entity.Admin;

@Repository
public interface IAdminRepo extends JpaRepository<Admin, Long> {

	public Admin findByEmail(String email);

}
