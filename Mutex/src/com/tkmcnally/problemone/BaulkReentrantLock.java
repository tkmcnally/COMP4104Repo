package com.tkmcnally.problemone;

import com.tkmcnally.sync.Sync;

public class BaulkReentrantLock implements Sync {

	private boolean isLocked = false;
	Thread lockedBy = null;
	int lockedCount = 0;
	private int attemptCount = 0;
	private int attempts = 0;

	public BaulkReentrantLock(int attempts) {
		this.attempts = attempts;
	}

	@Override
	public synchronized void acquire() {
		attemptCount++;

		try {
			if(attemptCount > attempts) {
				System.out.println(attempts);
				throw new BaulkException("Exceeded threshold of " + attempts + " to acquire lock.");
			}
		} catch (BaulkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Thread callingThread = Thread.currentThread();
		while(isLocked && lockedBy != callingThread) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		isLocked = true;
		lockedCount++;
		lockedBy = callingThread;


	}

	@Override
	public boolean attempt(int ms) throws InterruptedException {
		if(!isLocked) {
			isLocked = true;
			return true;
		} else if(ms <= 0) {
			return false;
		} else {
			long waitTime = ms;
			long startTime = System.currentTimeMillis();
			while(true) {
				wait(waitTime);
				if(!isLocked) {
					isLocked = true;
					return true;
				} else {
					waitTime = ms - (System.currentTimeMillis() - startTime);
					if(waitTime <= 0) {
						return false;
					}
				}
			}
		}

	}

	@Override
	public synchronized void release() {
		lockedCount--;
		if(lockedCount == 0) {
			attemptCount = 0;
			isLocked = false;
			notify();
		}
	}

}
