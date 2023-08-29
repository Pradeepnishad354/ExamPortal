package com.exam.helper;

public class UserFoundException  extends Exception  {
	
	
	public UserFoundException(String msg) {
		
		super(msg);
		
		
	}

	
	public UserFoundException() {
		
		super("user with this Username is already there in DB");
	}
}
