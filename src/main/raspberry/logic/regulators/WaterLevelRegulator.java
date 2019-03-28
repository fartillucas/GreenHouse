package raspberry.logic.regulators;

import raspberry.communication.CommunicationFacade;
import raspberry.logic.schedule.Schedule;

import static java.lang.Thread.sleep;

public class WaterLevelRegulator implements Runnable{
	public void waterLevelRegulator(int sec){
		CommunicationFacade.getInstance().getGreenhouseConnection().addWater(5);
	}

	@Override
	public void run() {
		while (true){
			try {



				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}
}
