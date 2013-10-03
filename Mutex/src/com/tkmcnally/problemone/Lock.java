package com.tkmcnally.problemone;

public class Lock implements Runnable {

	private final Resource resource;
	private final ReentrantLock lock;
	private int timeout = 0;
	//private final BaulkReentrantLock lock;

	public Lock(Resource resource, ReentrantLock lock, int ms) {
		this.resource = resource;
		this.lock = lock;
		this.timeout = ms;
	}

	/*	public Lock(Resource resource, BaulkReentrantLock lock) {
		this.resource = resource;
		this.lock = lock;
	}*/


	@Override
	public void run() {

		lock.acquire();
		//lock.acquire();
		try{
			try {
				Thread.sleep(timeout);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resource.dolt();
		} finally {
			lock.release();
		}



	}

}
