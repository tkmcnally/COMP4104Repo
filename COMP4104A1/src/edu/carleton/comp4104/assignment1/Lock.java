package edu.carleton.comp4104.assignment1;

public class Lock{

	boolean isLocked = false;
	Thread  lockedBy = null;
	int     lockedCount = 0;

	public synchronized void acquire()
			throws InterruptedException{
		Thread callingThread = Thread.currentThread();
		while(isLocked && lockedBy != callingThread){
			wait();
		}
		isLocked = true;
		lockedCount++;
		lockedBy = callingThread;
	}


	public synchronized void release(){
		if(Thread.currentThread() == this.lockedBy){
			lockedCount--;

			if(lockedCount == 0){
				isLocked = false;
				notify();
			}
		}
	}

}