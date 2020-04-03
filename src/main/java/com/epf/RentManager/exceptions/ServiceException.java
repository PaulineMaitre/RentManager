package com.epf.RentManager.exceptions;

public class ServiceException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * constructor of ServiceException without any parameters
	 */
	public ServiceException() {
		super();
	}
	
	/**
	 * constructor of ServiceException with the error message as a parameter
	 * @param msg : error message
	 */
	public ServiceException(String msg) {
		super(msg);
	}
}
