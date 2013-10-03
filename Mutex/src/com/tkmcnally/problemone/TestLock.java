package com.tkmcnally.problemone;

public class TestLock {

	public static void main(String[] args) throws InterruptedException {
		ReentrantLock lock = new ReentrantLock(10000);
		//	BaulkReentrantLock lock = new BaulkReentrantLock(2);
		Thread t1 = new Thread(new Lock(new Resource("E:/Dropbox/Dropbox/COMP4104/Deadlock/Deadlock/resource_one.txt"), lock, 3000));
		Thread t2 = new Thread(new Lock(new Resource("E:/Dropbox/Dropbox/COMP4104/Deadlock/Deadlock/resource_two.txt"), lock, 2000));
		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println("DONE!");
	}

}
