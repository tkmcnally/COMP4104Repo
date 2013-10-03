package edu.carleton.comp4104.assignment1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

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

		Character CONTEXT_ONE_BRACKET = null;
		Character CONTEXT_TWO_BRACKET = null;
		int CONTEXT_ONE_START = 0;
		int CONTEXT_TWO_START = 0;
		
		List<Character> ASYNC_LIST = new ArrayList<Character>();
		List<Character> CONTEXT_ONE_LIST = new ArrayList<Character>();
		List<Character> CONTEXT_TWO_LIST = new ArrayList<Character>();
		
		boolean innerClosed = false;
		
		for(int i = 0; i < characters.size(); i++) {
			if(CONTEXT_TWO_BRACKET != null) {
				if(characters.get(i).equals(matchingContext(CONTEXT_TWO_BRACKET)) && innerClosed == false) {
					//start_nested_threads(CONTEXT_ONE_LIST, CONTEXT_TWO_LIST, CONTEXT_ONE_BRACKET, CONTEXT_TWO_BRACKET);
					//CONTEXT_TWO_BRACKET = null;
					innerClosed = true;
					
				} else if(characters.get(i).equals(matchingContext(CONTEXT_ONE_BRACKET))) {
					start_nested_threads(CONTEXT_ONE_LIST, CONTEXT_TWO_LIST, CONTEXT_ONE_BRACKET, CONTEXT_TWO_BRACKET);
					CONTEXT_TWO_BRACKET = null;
					CONTEXT_TWO_LIST = new ArrayList<Character>();
				} else {
					if(innerClosed) {
						CONTEXT_ONE_LIST.add(characters.get(i));
					} else {
						CONTEXT_TWO_LIST.add(characters.get(i));
					}
				}
			} else if(CONTEXT_ONE_BRACKET != null) {
				
				if(characters.get(i).equals(matchingContext(CONTEXT_ONE_BRACKET))) {			
					//start_threads(CONTEXT_ONE_BRACKET, CONTEXT_ONE_LIST);
					start_nested_threads(CONTEXT_ONE_LIST, null, CONTEXT_ONE_BRACKET, new Character('?'));
					CONTEXT_ONE_BRACKET = null;
					CONTEXT_ONE_LIST = new ArrayList<Character>();
				} else if(characters.get(i).equals('[') || characters.get(i).equals('{')) {
					CONTEXT_TWO_BRACKET = characters.get(i);
					CONTEXT_TWO_START  = i;
				} else {
					CONTEXT_ONE_LIST.add(characters.get(i));
				}
				
				
			}  else {
				if(characters.get(i).equals('[') || characters.get(i).equals('{')) { 
					CONTEXT_ONE_BRACKET = characters.get(i);
					CONTEXT_ONE_START = i;
				} else {
					ASYNC_LIST.add(characters.get(i));
				//	start_threads(null, ASYNC_LIST);
					ASYNC_LIST.clear();
				}
			}
		}
		
		
	}
	
	public void start_nested_threads(List<Character> outerList, List<Character> innerList, Character outerBracket, Character innerBracket) {
		
		if(outerBracket.equals('[')) {
			
			if(innerBracket == '{') {
				
				
				
			} else if (innerBracket == '[') {
				CountDownLatch latch1 = new CountDownLatch(outerList.size());
				CountDownLatch latch2 = new CountDownLatch(outerList.size());
				Thread t = new Thread(new ThreadGroup(outerList, new ThreadGroup(innerList, latch2), latch1));
				t.start();
				try {
					t.join();
				} catch (InterruptedException e) {}
			} else {
				Thread t = new Thread(new ThreadGroup(outerList));
				t.start();
				try {
					t.join();
				} catch (InterruptedException e) {}
			}
		
		} else if(outerBracket.equals('{')) {
			
			if(innerBracket == null) {
			//	Thread t = new Thread(new ThreadRace(outerList));
			//	t.start();
			//	try {
			//		t.join();
			//	} catch (InterruptedException e) {}
			} else {
				if(outerBracket.equals('{')) {
					
				} else {
					
				}
			}
	
		}
	}
	
	/*public void start_threads(Character BRACKET, List<Character> list) {
		if(BRACKET == null) {
			Thread t = new Thread(new PrintWorker(list.get(0), null));
			t.start();
			try {
				t.join();
			} catch (InterruptedException e) {}
		} else if(BRACKET.equals('[')) {
			Thread t = new Thread(new ThreadGroup(list));
			t.start();
			try {
				t.join();
			} catch (InterruptedException e) {}
		} else if(BRACKET.equals('{')) {
			Thread t = new Thread(new ThreadRace(list));
			t.start();
			try {
				t.join();
			} catch (InterruptedException e) {}
		}
	}*/

}
