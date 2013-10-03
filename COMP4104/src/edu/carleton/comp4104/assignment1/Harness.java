package edu.carleton.comp4104.assignment1;

import java.util.concurrent.CyclicBarrier;


public class Harness {

	public static int numberOfWorkers = 10;
	
	public static void main(String[] args) {
		Controller controller =  new Controller(10);
		CyclicBarrier b = new CyclicBarrier(numberOfWorkers, controller);
		char a = 'a';
		for(int i = 0; i < numberOfWorkers; i++) {
			
			Work work = new Work(i, b, a++);
			controller.put(work);
			Thread t = new Thread(work);
		
			
			try {
				
				t.start();
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
