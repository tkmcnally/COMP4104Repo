package edu.carleton.comp4104.assignment1;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ThreadGroup implements Runnable {

	private final List<Character> characters;
	private ThreadRace threadRace;
	private ThreadGroup threadGroup;
	private CountDownLatch latch;
	private final int TYPE;
	
	private final int NESTED_GROUP_GROUP = 1;
	private final int NESTED_GROUP_RACE = 2;
	private final int NON_NESTED_LATCH = 3;
	private final int NON_NESTED = 4;
	
	public ThreadGroup(List<Character> characters) {
		this.characters = characters;
		this.TYPE = 4;
		this.latch = new CountDownLatch(characters.size());
	}
	
	public ThreadGroup(List<Character> characters, CountDownLatch latch) {
		this.characters = characters;
		this.latch = latch;
		this.TYPE = 3;
		
	}
	
	public ThreadGroup(List<Character> characters, ThreadGroup threadGroup, CountDownLatch latch) {
		this.characters = characters;
		this.threadGroup = threadGroup;
		this.latch = latch;
		this.TYPE = 1;
	}
	
	public ThreadGroup(List<Character> characters, ThreadRace threadRace) {
		this.characters = characters;
		this.threadRace = threadRace;
		this.TYPE = 2;
	}
	
	@Override
	public void run() {		
		try {	
			switch (TYPE) {
				case NESTED_GROUP_GROUP:
					
					for(Character c: characters) {
						new Thread(new PrintWorker(c, latch)).start();
					}
					
					Thread NESTED_GROUP = new Thread(threadGroup);
					NESTED_GROUP.start();
					
					latch.await();
					
					break;
				case NESTED_GROUP_RACE:
					
					CountDownLatch latch1 = new CountDownLatch(characters.size() + 1);
					for(Character c: characters) {
						new Thread(new PrintWorker(c, latch1)).start();
					}
					latch1.await();
					Thread t = new Thread(threadGroup);
					t.start();
					
				
					
					break;
				
				case NON_NESTED_LATCH:
					
					CountDownLatch latch2 = new CountDownLatch(characters.size());
					for(Character c: characters) {
						new Thread(new PrintWorker(c, latch2)).start();
					}
					
					latch2.await();
					latch.countDown();
					
					break;
				case NON_NESTED:
					
					for(Character c: characters) {
						new Thread(new PrintWorker(c, latch)).start();
					}
					
					latch.await();
					break;
			}
					
				
				
		
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	

}
