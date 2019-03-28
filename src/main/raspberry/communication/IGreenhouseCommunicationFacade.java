package raspberry.communication;


import raspberry.communication.greenhouseconnection.GreenhouseConnectionFacade;

import java.util.BitSet;

public interface IGreenhouseCommunicationFacade {

	GreenhouseConnectionFacade getGreenhouseConnection();

	Double readTemp1();
	Double readTemp2();
	Double readMoist();
	Double readWaterLevel();
	BitSet readErrors();
	boolean addWater(int sec);
	boolean setRedLight(int level);
	boolean setBlueLight(int level);
	boolean setTemperature(int kelvin);
	boolean setFanSpeed(int speed);


}

