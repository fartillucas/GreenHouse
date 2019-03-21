package raspberry.communication;


import raspberry.communication.greenhouseconnection.GreenhouseConnectionFacade;
import main.Greenhouse;

import java.util.BitSet;

public interface IGreenhouseCommunicationFacade {

	GreenhouseConnectionFacade getGreenhouseConnection();

	double readTemp1();
	double readTemp2();
	double readMoist();
	double readWaterLevel();
	BitSet readErrors();
	boolean addWater();
	boolean setRedLight();
	boolean setBlueLight();
	double setTemperature();
	boolean setFanSpeed();


}

