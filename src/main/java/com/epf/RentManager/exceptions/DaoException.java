package com.epf.RentManager.exceptions;

public class DaoException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * constructor of DaoException without any parameters
	 */
	public DaoException() {
		super();
	}
	
	/**
	 * constructor of DaoException with the error message as a parameter
	 * @param msg : error message
	 */
	public DaoException(String msg) {
		super(msg);
	}
}
