package raspberry.communication;

import raspberry.communication.greenhouseconnection.GreenhouseConnectionFacade;

import java.util.BitSet;

public class CommunicationFacade implements IGreenhouseCommunicationFacade{
	private static CommunicationFacade instance;
	private GreenhouseConnectionFacade greenhouse;


	public static CommunicationFacade getInstance() {
		if (CommunicationFacade.instance == null) {
			CommunicationFacade.instance = new CommunicationFacade();
		}
		return CommunicationFacade.instance;
	}

	public CommunicationFacade() {
		this.greenhouse = new GreenhouseConnectionFacade();
	}

	public GreenhouseConnectionFacade getGreenhouseConnection() {
		return this.greenhouse;
	}

	@Override
	public double readTemp1() {
		return 0;
	}

	@Override
	public double readTemp2() {
		return 0;
	}

	@Override
	public double readMoist() {	 return 0; }

	@Override
	public double readWaterLevel() { return 0; }

	@Override
	public BitSet readErrors() {return null;
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


}
