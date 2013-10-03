package edu.carleton.comp4104.assignment1;


public class Main {
	public static void main(String[] args) {
		String num = "[12['4']6]";
		InputMap i = new InputMap(num);
		try {
			i.start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}




