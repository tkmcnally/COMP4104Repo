import java.nio.channels.Channel;


public class Hybrid implements Runnable {

	private Thread THREAD_PRODUCER;
	private Thread THREAD_CONSUMER;
	private final Channel bi_channel;
	private final int ID;
	
	public Hybrid(Channel bi_channel, int ID) {
		this.bi_channel = bi_channel;
		this.ID = ID;
	}
	
	
	@Override
	public void run() {
		
		
	}
	
	public synchronized void recieveMessage(String message) {
		System.out.println("Thread " +ID + " recieved: " + message);
	}
	
	public synchronized void sendMessage(String message) {
		
	}
	
}
