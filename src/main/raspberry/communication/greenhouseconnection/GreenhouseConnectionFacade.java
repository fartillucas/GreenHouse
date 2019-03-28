package raspberry.communication.greenhouseconnection;

import Mocks.Mocks.GreenhouseMock;
import main.IGreenhouse;
import raspberry.communication.communicationAquaintance.IGreenhouseConnectionFacade;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.BitSet;

public class GreenhouseConnectionFacade implements IGreenhouseConnectionFacade {

	//Greenhouse greenhouseAPI = new Greenhouse();
	IGreenhouse greenhouseAPI = GreenhouseMock.getInstance();

	@Override
	public Double readTemp1() {
		try {
			return greenhouseAPI.ReadTemp1();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Double readMoist() {
		try {
			return greenhouseAPI.ReadMoist();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Double readWaterLevel() {
		try {
			return greenhouseAPI.ReadWaterLevel();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public BitSet readErrors() {
		throw new NotImplementedException();
	}

	@Override
	public boolean addWater(int sec) {
		return this.greenhouseAPI.AddWater(sec);
	}

	@Override
	public boolean setRedLight(int level) {
		return this.greenhouseAPI.SetRedLight(level);
	}

	@Override
	public boolean setBlueLight(int level) {
		return this.greenhouseAPI.SetBlueLight(level);
	}

	@Override
	public boolean setTemperature(int kelvin) {
		return this.greenhouseAPI.SetTemperature(kelvin);
	}


	@Override
	public boolean setFanSpeed(int speed) {
		return this.greenhouseAPI.SetFanSpeed(speed);
	}

	@Override
	public Double readTemp2() {
		try {
			return greenhouseAPI.ReadTemp2();
		} catch (Exception e) {
			return null;
		}
	}

}
