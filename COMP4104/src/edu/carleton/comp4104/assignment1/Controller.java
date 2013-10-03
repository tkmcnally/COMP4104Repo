package edu.carleton.comp4104.assignment1;

public class Controller implements Runnable {

	private final int i = 0;
	private final Work[] workers;
	private int size;

	public Controller(int i) {
		this.workers = new Work[i];
	}

	public void put(Work worker) {
		workers[size] = worker;
		size++;
	}
	
	@Override
	public void run() {
		System.out.println("asd");

	}

}
