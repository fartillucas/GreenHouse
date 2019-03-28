package raspberry.logic.subscribers;

import raspberry.communication.CommunicationFacade;

import static java.lang.Thread.sleep;

public class WaterLevelSubscriber implements Runnable{

	public void waterLevelSubscriber(){
		CommunicationFacade.getInstance().getGreenhouseConnection().readWaterLevel();

	}
	@Override
	public void run() {

		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

