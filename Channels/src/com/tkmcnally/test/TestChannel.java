package com.tkmcnally.test;

import com.tkmcnally.channel.Channel;
import com.tkmcnally.channel.impl.BoundedArray;

public class TestChannel {

	private static int endianConversion(int thisInteger) {
		return ((thisInteger & 0x000000ff) << 24) + ((thisInteger & 0x0000ff00) << 8) + ((thisInteger & 0x00ff0000) >>> 8) + ((thisInteger & 0xff000000) >>> 24);
	}

	public static void main(String[] args) {
		testChannelImpl(new BoundedArray(10));
		//testChannelImpl(new Queue());
	}

	public static void testChannelImpl(Channel ch) {

		Channel channel = ch;

		Thread p1 = new Thread(new Producer(channel, 0));
		Thread c1 = new Thread(new Consumer(channel, 0));

		p1.start();
		c1.start();
	}

}
