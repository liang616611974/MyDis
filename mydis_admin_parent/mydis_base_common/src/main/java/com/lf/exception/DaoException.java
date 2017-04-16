package com.lf.exception;

public class DaoException extends RuntimeException{
	private static final long serialVersionUID = 1487159104443022977L;
	public DaoException(){
    	super();
    }
    public DaoException(String message){
    	super(message);
    }
    public DaoException(String message,Exception e){
    	super(message,e);
    }
}
