package edu.carleton.comp4104.assignment1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Block implements Runnable {

	private final int GROUP_GROUP = 2;
	private final int GROUP_RACE = 3;
	private final int RACE_GROUP = 4;
	private final int RACE_RACE = 5;
	private final int INNER_GROUP = 6;
	private final int INNER_RACE = 7;
	

	private final int GROUP = 0;
	private final int RACE = 1;


	private final List<Character> characters;
	private final Lock lock;
	private Block block;
	private final int BLOCK_TYPE;
	private List<Thread> threads;

	public Block(Lock lock, List<Character> characters, int type) {
		this.lock = lock;
		this.characters = characters;
		this.BLOCK_TYPE = type;
	}

	public Block(Lock lock, List<Character> characters, Block block, int type) {
		this.lock = lock;
		this.characters = characters; 
		this.block = block;
		this.BLOCK_TYPE = type;
	}

	@Override
	public void run() {
		switch(BLOCK_TYPE) {
		case GROUP_GROUP:
			try {
			RUN_GROUP_GROUP();
			} catch (InterruptedException e) {}
			break;
		case GROUP_RACE:
			try {
			RUN_GROUP_RACE();
			} catch (InterruptedException e) {}
			break;
		case RACE_GROUP:
			try {
			RUN_RACE_GROUP();
			} catch (InterruptedException e) {}
			break;
		case RACE_RACE:
			try {
			RUN_RACE_RACE();
			} catch (InterruptedException e) {}
			break;
		case GROUP:
			try {
				RUN_GROUP();
			} catch (InterruptedException e) {}
			break;
		case RACE:
			try {
			RUN_RACE();
			} catch (InterruptedException e) {}
			break;
		case INNER_RACE:
			try {
			RUN_INNER_RACE();
			} catch (InterruptedException e) {}
			break;
		case INNER_GROUP:
			try {
			RUN_INNER_GROUP();
			} catch (InterruptedException e) {}
			break;
	}
}

	public void RUN_GROUP() throws InterruptedException {
		lock.acquire();
		try {
		CountDownLatch latch = new CountDownLatch(characters.size());
		for(Character c: characters) {
			new Thread(new PrintWorker(c, latch)).start();
		}
		
		latch.await();
		} finally {
			lock.release();
		}
	}

	public void RUN_GROUP_GROUP() throws InterruptedException {
		lock.acquire();
		CountDownLatch latch = new CountDownLatch(characters.size() + 1);
		for(Character c: characters) {
			new Thread(new PrintWorker(c, latch)).start();
		}
		
		Thread t = new Thread(block);
		t.start();
		
		latch.await();
		lock.release();
	}

	public void RUN_GROUP_RACE() throws InterruptedException {
		lock.acquire();
		lock.release();
	}

	public void RUN_RACE() throws InterruptedException {
		lock.acquire();
		threads = new ArrayList<Thread>();
		CountDownLatch latch = new CountDownLatch(1);
		for(Character c: characters) {
			Thread t = new Thread(new PrintWorker(c, latch));
			threads.add(t);
			t.start();
		}
		
		latch.await();
		terminateThreads();
		lock.release();
	}

	public void RUN_RACE_GROUP() throws InterruptedException {
		lock.acquire();
		lock.release();
	}

	public void RUN_RACE_RACE() throws InterruptedException {
		lock.acquire();
		
		
		
		lock.release();
	}
	
	public void RUN_INNER_RACE() throws InterruptedException {
		lock.acquire();
		
		
		
		lock.release();
	}
	
	public void RUN_INNER_GROUP() throws InterruptedException {
			Thread t = lock.overrideLock();
			CountDownLatch latch = new CountDownLatch(characters.size());
			for(Character c: characters) {
				new Thread(new PrintWorker(c, latch)).start();
			}
			latch.await();
			lock.overrideLock(t);
			
	}
	
	public void terminateThreads() {
		for(Thread thread : threads) {
		    thread.interrupt();
		}			
	}
}

