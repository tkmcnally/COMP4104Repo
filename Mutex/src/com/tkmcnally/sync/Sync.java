package com.tkmcnally.sync;

public interface Sync {

	public void acquire() throws InterruptedException;
	public boolean attempt(int msecs) throws InterruptedException;
	public void release();

}
