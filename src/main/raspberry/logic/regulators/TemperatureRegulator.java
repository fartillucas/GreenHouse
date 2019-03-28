package raspberry.logic.regulators;

import raspberry.communication.CommunicationFacade;
import raspberry.logic.SetPoints;
import raspberry.logic.currentmeasurements.CurrentMeasurementsFacade;
import raspberry.logic.schedule.Schedule;

import static java.lang.Thread.sleep;

public class TemperatureRegulator implements Runnable {
	public void temperatureRegulator(int kelvin) {
		CommunicationFacade.getInstance().getGreenhouseConnection().setTemperature(kelvin);
	}
	@Override
	public void run() {
		try {
			SetPoints setPoints = Schedule.getInstance().getSetpoints();
			double scheduleTemp = setPoints.getTemperature();
			Double currentTemp = CurrentMeasurementsFacade.getInstance().getTemp();
			if (currentTemp<scheduleTemp)
				temperatureRegulator((int) scheduleTemp);

			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}