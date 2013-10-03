import java.util.concurrent.BlockingQueue;


public class BiDirectional {

	private BlockingQueue<Object> CHANNEL_1;
	private BlockingQueue<Object> CHANNEL_2;
	
	private Thread THREAD_A_P;
	private Thread THREAD_A_C;
	private Thread THREAD_B_P;
	private Thread THREAD_B_C;
	
	public BiDirectional() {
		
	}
	
	public void registerThreads(Thread sender, Thread receiver) {
		if(THREAD_A_P == null && THREAD_A_C == null) {
			THREAD_B_P = sender;
			THREAD_B_C = receiver;
		} else {
			THREAD_A_P = sender;
			THREAD_A_C = receiver;
		}
		
	}
	
	public synchronized void put(Object o) throws InterruptedException {
		if(Thread.currentThread() == THREAD_A_P) {
			CHANNEL_1.put(o);
		} else {
			CHANNEL_2.put(o);
		}
	}
	public synchronized Object take() throws InterruptedException {
		Object removedObject;
		if(Thread.currentThread() == THREAD_A_C) {
			removedObject = CHANNEL_2.take();
		} else {
			removedObject = CHANNEL_1.take();
		}
		
		return removedObject;
		
	}
	
	
}
