package com.tkmcnally.problemone;

public class BaulkException extends Exception {

	public BaulkException() { super(); }
	public BaulkException(String message) { super(message); }
	public BaulkException(String message, Throwable cause) { super(message, cause); }
	public BaulkException(Throwable cause) { super(cause); }

}
