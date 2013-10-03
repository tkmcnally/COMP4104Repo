package com.tkmcnally.test;

import com.tkmcnally.channel.Channel;

public class Consumer implements Runnable {

	private final Channel ch;
	private final int delay;

	public Consumer(Channel ch, int delay) {
		this.ch = ch;
		this.delay = delay;
	}

	@Override
	public void run() {
		try {
			while(ch.take() != null) {
				Thread.sleep(delay);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
