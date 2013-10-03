package com.tkmcnally.deadlock;



public class Deadlock implements Runnable {

	private final String name;
	private final Resource r1, r2;

	public Deadlock(String name, Resource resource1, Resource resource2) {
		this.name = name;
		this.r1 = resource1;
		this.r2 = resource2;
	}

	@Override
	public void run() {
		synchronized(r1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
			synchronized(r2) {
				r1.dolt();
				r2.dolt();
			}
		}
	}


}
