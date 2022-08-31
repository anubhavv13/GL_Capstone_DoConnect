package com.wipro.doconnect.exception;

/*
* @Author : sourabh
* Modified last date: 30-08-22
* Description :NotFound Class
*/

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class NotFound extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String errorMsg;

}
