package com.wipro.doconnect.repository;
/*
* @Author : sourabh
* Modified last date: 30-08-22
* Description :interface of ImageModelRepository
*/
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.doconnect.entity.ImageModel;

@Repository
public interface IImageModelRepo extends JpaRepository<ImageModel, Long> {

	public Optional<ImageModel> findByName(String name);

}
