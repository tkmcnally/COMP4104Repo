package edu.carleton.comp4104.assignment1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ThreadRace implements Runnable {

	private final List<Character> characters;
	private ThreadGroup threadGroup;
	private ThreadRace threadRace;
	private final List<Thread> threads;
	
	public ThreadRace(List<Character> characters, ThreadGroup threadGroup) {
		this.characters = characters;
		this.threadGroup = threadGroup;
		this.threads = new ArrayList<Thread>();
	}
	
	public ThreadRace(List<Character> characters, ThreadRace threadRace) {
		this.characters = characters;
		this.threadRace = threadRace;
		this.threads = new ArrayList<Thread>();
	}
	
	@Override
	public void run() {
		try {
		
			CountDownLatch latch = new CountDownLatch(1);
			
			for(Character c: characters) {
				Thread t = new Thread(new PrintWorker(c, latch));
				threads.add(t);
				t.start();
			}
			
			if(threadGroup != null) {
				Thread t = new Thread(threadGroup);
				threads.add(t);
				t.start();
			}
		
			
			latch.await();
			terminateThreads();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void terminateThreads() {
		for(Thread thread : threads) {
		    thread.interrupt();
		}			
	}
}
