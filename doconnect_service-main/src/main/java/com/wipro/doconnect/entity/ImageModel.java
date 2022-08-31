package com.wipro.doconnect.entity;
/*
* @Author : Sourabh
* Modified last date: 30-08-22
* Description :Image Entity
*/
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Data
@AllArgsConstructor
public class ImageModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private String type;

	// image bytes can have large lengths so we specify a value
	// which is more than the default length for picByte column
	@Column(name = "picByte", length = 100000)
	private byte[] picByte;

	public ImageModel() {
		super();
	}

	public ImageModel(String name, String type, byte[] picByte) {
		this.name = name;
		this.type = type;
		this.picByte = picByte;
	}



}
