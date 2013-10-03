package com.tkmcnally.test;

import com.tkmcnally.channel.Channel;
import com.tkmcnally.util.Constants;

public class Producer implements Runnable {

	private final Channel ch;
	private final int delay;
	int counter = 0;

	public Producer(Channel ch, int delay) {
		this.ch = ch;
		this.delay = delay;
	}

	@Override
	public void run() {
		try {
			while(counter < Constants.PRODUCER_LIMIT) {
				Thread.sleep(delay);
				System.out.println("asd");
			}
			ch.put(null);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
