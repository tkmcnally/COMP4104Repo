package com.tkmcnally.channel.impl;

import com.tkmcnally.channel.Channel;

public class BoundedArray implements Channel {

	protected Object[] buf;
	protected int in = 0;
	protected int out = 0;
	protected int count = 0;
	protected int size;

	public BoundedArray(int size) {
		this.size = size;
		buf = new Object[size];
	}

	@Override
	public synchronized void put(Object o) throws InterruptedException {

		while(count == size) {
			wait();
		}
		buf[in] = o;
		in = (in + 1) % size;
		System.out.println("Put " + o + " into buffer");
		if(count++ == 0) {
			notifyAll();
		}

	}

	@Override
	public synchronized Object take() throws InterruptedException {
		while(count == 0) {
			wait();
		}
		Object o = buf[out];
		out = (out + 1) % size;
		System.out.println("Removed " + o + " from buffer");
		if(count-- == size) {
			notifyAll();
		}


		return o;
	}



}
