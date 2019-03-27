package raspberry.logic.regulators;

import raspberry.communication.CommunicationFacade;

import static java.lang.Thread.sleep;

public class FanSpeedRegulator implements Runnable{
	public void fanSpeedRegulator(int speed){
		CommunicationFacade.getInstance().getGreenhouseConnection().setFanSpeed(1);
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
