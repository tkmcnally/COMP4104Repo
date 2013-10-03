package edu.carleton.comp4104.assignment1;

import java.util.ArrayList;
import java.util.List;

public class InputMap {
	
	private final List<Character> characters;
	private final String input; 
	
	public InputMap(String input) {
		this.input = input;
		characters = new ArrayList<Character>();
		for(char c: input.toCharArray()) {
			characters.add(c);
		}	
	}
	
	public Character matchingContext(Character c) {
		Character RET_CHAR = null;
		if(c.equals('[')) {
			RET_CHAR = ']';
		} else if(c.equals('{')){
			RET_CHAR = '}';
		}
		return RET_CHAR;	
	}
	
	public String sanitizeInput() {
		String sanitized = input;
		sanitized = sanitized.trim();
		return sanitized;
	}
	
	public void start() throws InterruptedException {
		Lock lock = new Lock();
		ArrayList<Character> temp = new ArrayList<Character>();
		temp.add('A');
		temp.add('D');
		Thread t = new Thread(new Block(lock, characters, new Block(lock, temp, 6), 2));
		t.start();
	}	
}