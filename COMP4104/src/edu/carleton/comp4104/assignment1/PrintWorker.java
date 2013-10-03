package edu.carleton.comp4104.assignment1;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class PrintWorker implements Runnable {

	private final char c;
	public CountDownLatch latch;
	private final Random r;
	
	public PrintWorker(char c, CountDownLatch latch) {
		this.c = c;
		r = new Random();
		this.latch = latch;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(r.nextInt(101));
			System.out.println(c);
			if(latch != null) {
				latch.countDown();
			}
		} catch (InterruptedException e) {}
		
	}

	
}