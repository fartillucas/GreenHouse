package raspberry.communication;

import raspberry.Acquaintance.ICommunications;
import raspberry.communication.communicationAquaintance.IDatabaseConnectionFacade;
import raspberry.communication.communicationAquaintance.IGreenhouseConnectionFacade;

import java.util.Date;

public class Communication implements ICommunications {

	//Should no longer require being singleton
	private static Communication instance;

	public static Communication getInstance() {
		if (Communication.instance == null) {
			Communication.instance = new Communication();
		}
		return Communication.instance;
	}

	private IDatabaseConnectionFacade databaseConnection;
	private IGreenhouseConnectionFacade greenhouse;

	public Communication() {
	}

	public void injectDatabaseConnection(IDatabaseConnectionFacade databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

	public void injectGreenhouse(IGreenhouseConnectionFacade greenhouse) {
		this.greenhouse = greenhouse;
	}

	@Override
	public IGreenhouseConnectionFacade getGreenhouseConnection() {
		return this.greenhouse;
	}

	@Override
	public void insertLog(String greenhouseId, Date timeOfReading, double internalTemperature, double extenalTemperature, double humidity, double waterlevel) {
		databaseConnection.insertLog(greenhouseId, timeOfReading, internalTemperature, extenalTemperature, humidity, waterlevel);
	}

}
