package raspberry.communication;

import raspberry.Acquaintance.ICommunicationsFacade;
import raspberry.Acquaintance.IWebAppConnectionFacade;
import raspberry.Acquaintance.ReadableIPAddressPort;
import raspberry.Acquaintance.ServerInfoEnum;
import raspberry.communication.communicationAquaintance.IDatabaseConnectionFacade;
import raspberry.communication.communicationAquaintance.IGreenhouseConnectionFacade;

import java.util.Date;

public class CommunicationFacade implements ICommunicationsFacade {

	private IDatabaseConnectionFacade databaseConnection;
	private IGreenhouseConnectionFacade greenhouse;
	private IWebAppConnectionFacade webAppConnectionFacade;

	public CommunicationFacade() {
	}

	public void injectWebAppConnectionFacade(IWebAppConnectionFacade webAppConnectionFacade){
		this.webAppConnectionFacade = webAppConnectionFacade;
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

	@Override
	public boolean petWatchdog(String greenhouseID) {
		//TODO DO THIS!!!
		return webAppConnectionFacade.petTheDog(greenhouseID, ServerInfoEnum.SERVERINFO.getIP(), ServerInfoEnum.SERVERINFO.getPort());

	}

	@Override
	public void setServerInfo(String serverIP, int serverPort) {

	}

}
