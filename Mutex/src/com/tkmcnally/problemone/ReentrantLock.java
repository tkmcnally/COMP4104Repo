package com.tkmcnally.problemone;

import com.tkmcnally.sync.Sync;

public class ReentrantLock implements Sync {

	private boolean isLocked = false;
	Thread lockedBy = null;

	int lockedCount = 0;

	private int timeout = 0;
	private final int timeCounter = 0;

	public ReentrantLock() {

	}

	public ReentrantLock(int timeout) {
		this.timeout = timeout;
	}

	@Override
	public synchronized void acquire() {
		acquireLock();

		new Thread(new Runnable() {
			@Override
			public void run() {
				if(timeout > 0) {
					long waitTime = timeout;
					long startTime = System.currentTimeMillis();
					while(isLocked) {
						waitTime = timeout - (System.currentTimeMillis() - startTime);
						if(waitTime <= 0) {
							System.out.println("Timedout.");
							release();
						}
					}
				}
			}
		}).start();;
	}

	private synchronized void acquireLock() {
		Thread callingThread = Thread.currentThread();
		while(isLocked && lockedBy != callingThread) {
			try {
				System.out.println("waiting");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		isLocked = true;
		System.out.println("LOCK ACQUIRED");
		lockedCount++;
		lockedBy = callingThread;
	}

	@Override
	public boolean attempt(int ms) throws InterruptedException{
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
			System.out.println("LOCK RELEASED");
			isLocked = false;
			notify();
		}
	}

}
