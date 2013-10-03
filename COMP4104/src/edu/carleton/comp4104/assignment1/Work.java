package edu.carleton.comp4104.assignment1;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class Work implements Runnable {

	private final Random random;
	private final CyclicBarrier barrier;
	private final int index;
	private final char character;
	public boolean locked = true;
	public boolean finished = false;

	public Work(int index, CyclicBarrier barrier, char character) {
		this.index = index;
		this.barrier = barrier;
		this.character = character;
		random = new Random();
	}

	public synchronized void print() {
		System.out.println(character);
		this.finished = true;
	}
	
	@Override
	public void run() {
		
		while(true && !finished) {
			try {
				Thread.sleep(random.nextInt(101));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
			System.out.println(character);
			this.finished = true;		
			
		
		}				        
	}
}
