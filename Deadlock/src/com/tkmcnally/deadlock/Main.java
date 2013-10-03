package com.tkmcnally.deadlock;

import java.util.ArrayList;
import java.util.List;

import com.tkmcnally.deadlock.constants.Constants;

public class Main {


	public static void main(String[] args) throws Exception{


		Resource r1 = new Resource(Constants.RESOURCE1_PATH);
		Resource r2 = new Resource(Constants.RESOURCE2_PATH);

		Deadlock d1 = new Deadlock("Deadlock1", r1, r2);
		Deadlock d2 = new Deadlock("Deadlock2", r2, r1);

		Thread thread1 = new Thread(d1);
		Thread thread2 = new Thread(d2);

		List<Thread> threads = new ArrayList<Thread>();

		threads.add(thread1);
		threads.add(thread2);

		thread1.start();
		thread2.start();

		for(Thread toThread : threads) {

			toThread.join();
		}

		System.out.println("DONE!");

	}

}
