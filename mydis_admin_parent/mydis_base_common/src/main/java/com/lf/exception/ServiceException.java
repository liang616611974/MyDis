package com.lf.exception;

public class ServiceException extends RuntimeException{
  
	private static final long serialVersionUID = 2129888337773839216L;
	public ServiceException(){
    	super();
    }
    public ServiceException(String message){
    	super(message);
    }
    public ServiceException(String message,Exception e){
    	super(message,e);
    }
}
