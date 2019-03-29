package raspberry.logic.subscribers;

import static java.lang.Thread.sleep;

public class ErrorSubscriber implements Runnable{

	@Override
	public void run() {
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
