package raspberry.logic.regulators;

import raspberry.Acquaintance.ICurrentMeasurementsFacade;
import raspberry.Acquaintance.ISchedule;
import raspberry.Acquaintance.ReadableSetpoints;
import raspberry.logic.OutFacadeLogic;

import static java.lang.Thread.sleep;

public class FanSpeedRegulator implements Runnable{

	private boolean continueRunning;
	private ICurrentMeasurementsFacade currentMeasurements;
	private ISchedule schedule;

	private int lastSpeed;

	public FanSpeedRegulator(ICurrentMeasurementsFacade currentMeasurements, ISchedule schedule){
		this.currentMeasurements = currentMeasurements;
		this.schedule = schedule;
		this.lastSpeed = -1;
		continueRunning = true;
	}

	@Override
	public void run() {
		while (!Thread.interrupted() && continueRunning){
			try {
				ReadableSetpoints setpoints = schedule.getSetpoints();
				Double scheduleTemperature = setpoints.getTemperature();
				Double scheduleHumidity = setpoints.getHumidity();

				Double currentInternalTemperature = currentMeasurements.getInternalTemperature();
				Double currentHumidity = currentMeasurements.getHumdity();
				Double currentExternalTemperature = currentMeasurements.getExternalTemperature();

				int speed = 0;
				boolean regulateTemperature = (scheduleTemperature != null && currentInternalTemperature != null
						&& currentExternalTemperature != null);
				boolean regulateHumidity = (scheduleHumidity != null && currentHumidity != null);

				if (regulateHumidity && regulateTemperature){
					speed = calculateNormalRegulation(scheduleTemperature, scheduleHumidity, currentInternalTemperature, currentExternalTemperature, currentHumidity);
				} else if(regulateTemperature){
					speed = calculateNoHumidityRegulation(scheduleTemperature, currentInternalTemperature, currentExternalTemperature);
				} else if(regulateHumidity){
					speed = calculateNoTemperatureRegulation(scheduleHumidity, currentHumidity);
				} else {
					speed = calculateNoRegulation();
				}

				if (speed != lastSpeed){
					regulate(speed);
				}

				this.lastSpeed = speed;

				sleep(1000);
			} catch (InterruptedException e) {
				continueRunning = false;
			}
		}
	}

	private void regulate(int speed){
		OutFacadeLogic.getInstance().getGreenhouseConnection().setFanSpeed(speed);
	}

	private int calculateNormalRegulation(Double scheduleTemperature, Double scheduleHumidity, Double currentInternalTemperature, Double currentExternalTemperature, Double currentHumidity){
		//TODO this should be tuned
		double humidityFactor = 0.8;
		double temperatureFactor = 1.0;
		double temperatureDifference = 20;

		double humidityScore = humidityFactor*(currentHumidity-scheduleHumidity)*(currentInternalTemperature+temperatureDifference-currentExternalTemperature);
		double temperatureScore = temperatureFactor*((currentInternalTemperature-scheduleTemperature)*(currentInternalTemperature-currentExternalTemperature));

		double regulatorScore = humidityScore+temperatureScore;

		int speed;

		if (scheduleTemperature > currentInternalTemperature && currentExternalTemperature > currentInternalTemperature){
			return calculateNoTemperatureRegulation(scheduleHumidity, currentHumidity);
		}

		if (regulatorScore <= 0 ){
			speed = 0;
		} else if (regulatorScore>30) {
			speed = 2;
		} else {
			speed = 1;
		}

		return speed;
	}

	private int calculateNoTemperatureRegulation(Double scheduleHumidity, Double currentHumidity){
		Double humidityDifference = currentHumidity-scheduleHumidity;

		int speed;

		if (humidityDifference <= 0){
			speed = 0;
		} else if (humidityDifference>30) {
			speed = 2;
		} else {
			speed = 1;
		}

		return speed;
	}

	private int calculateNoHumidityRegulation(Double scheduleTemperature, Double currentInternalTemperature, Double currentExternalTemperature){

		double temperatureDifference = currentInternalTemperature-scheduleTemperature;

		int speed;

		if(currentExternalTemperature < currentInternalTemperature){
			if (temperatureDifference <= 0){
				speed = 0;
			} else if (temperatureDifference>10) {
				speed = 2;
			} else {
				speed = 1;
			}
		} else {
			speed = 0;
		}

		return speed;
	}

	private int calculateNoRegulation(){
		return 0;
	}
}
