package com.tkmcnally.channel.impl;

import java.util.ArrayList;
import java.util.List;

import com.tkmcnally.channel.Channel;

public class Queue implements Channel {

	protected List list;

	public Queue() {
		list = new ArrayList();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	//Queue must have Producers are slower than Consumers
	@Override
	public synchronized void put(Object o) throws InterruptedException {
		list.add(o);
		System.out.println("Put " + o + " into queue");
		notify();
	}

	@Override
	public synchronized Object take() throws InterruptedException {
		while(list.isEmpty()) {
			wait();
		}
		System.out.println("Removed " + list.get(0) + " from queue");
		return list.remove(0);
	}
}
