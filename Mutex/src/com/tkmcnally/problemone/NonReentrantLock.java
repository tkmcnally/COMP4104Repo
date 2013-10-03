package com.tkmcnally.problemone;

import com.tkmcnally.sync.Sync;

public class NonReentrantLock implements Sync {

	private boolean isLocked = false;


	@Override
	public synchronized void acquire() throws InterruptedException {
		while(isLocked) {
			wait();
		}
		isLocked = true;

	}

	@Override
	public boolean attempt(int msecs) throws InterruptedException {
		return false;
	}

	@Override
	public void release() {
		isLocked = false;
		notify();

	}

}
