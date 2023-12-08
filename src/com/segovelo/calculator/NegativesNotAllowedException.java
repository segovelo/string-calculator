package com.segovelo.calculator;

/** 
* 8 Dec 2023 22:35:17
* @Javadoc TODO 
*
* @author Segovelo  **/

public class NegativesNotAllowedException extends NumberFormatException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer number;
	
	public NegativesNotAllowedException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public NegativesNotAllowedException(Integer number) {
		super();
		this.number = number;
	}

	public Integer getNumber() {
		return number;
	}

	NegativesNotAllowedException(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}
}
