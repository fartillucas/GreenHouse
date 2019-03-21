package raspberry.communication.greenhouseconnection;

import main.Greenhouse;
import raspberry.communication.IGreenhouseCommunicationFacade;

import java.util.BitSet;


public class GreenhouseConnectionFacade implements IGreenhouseCommunicationFacade {

	Greenhouse greenhouseAPI = new Greenhouse();

	@Override
	public GreenhouseConnectionFacade getGreenhouseConnection() {

		return null;
	}
	@Override
	public double readTemp1(){

		return 0;
	}
	@Override
	public double readMoist() {
		return 0;
	}

	@Override
	public double readWaterLevel() {
		return 0;
	}

	@Override
	public BitSet readErrors() {
		return null;
	}

	@Override
	public boolean addWater() {
		return false;
	}

	@Override
	public boolean setRedLight() {
		return false;
	}

	@Override
	public boolean setBlueLight() {
		return false;
	}

	@Override
	public double setTemperature() {
		return 0;
	}

	@Override
	public boolean setFanSpeed() {
		return false;
	}

	@Override
	public double readTemp2(){

		return 0;
	}

}
