package raspberry.communication.greenhouseconnection;

import Mocks.Mocks.GreenhouseMock;
import main.Greenhouse;
import main.IGreenhouse;
import raspberry.communication.IGreenhouseCommunicationFacade;

import java.util.BitSet;

public class GreenhouseConnectionFacade implements IGreenhouseCommunicationFacade {

	//Greenhouse greenhouseAPI = new Greenhouse();
	IGreenhouse greenhouseAPI = GreenhouseMock.getInstanance();

	@Override
	public GreenhouseConnectionFacade getGreenhouseConnection() {

		GreenhouseMock.getInstanance();
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
	public boolean addWater(int sec) {
		return this.greenhouseAPI.AddWater(sec);
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
	public boolean setFanSpeed(int speed) {
		return this.greenhouseAPI.SetFanSpeed(speed);
	}

	@Override
	public double readTemp2(){

		return 0;
	}

}
