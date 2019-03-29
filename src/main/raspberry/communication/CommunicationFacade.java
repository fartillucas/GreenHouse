package raspberry.communication;

import raspberry.Acquaintance.ICommunicationsFacade;
import raspberry.communication.communicationAquaintance.IDatabaseConnectionFacade;
import raspberry.communication.communicationAquaintance.IGreenhouseConnectionFacade;
import raspberry.communication.databaseconnection.DatabaseConnectionFacade;
import raspberry.communication.greenhouseconnection.GreenhouseConnectionFacade;

public class CommunicationFacade implements ICommunicationsFacade {

	//Should no longer require being singleton
	private static CommunicationFacade instance;

	public static CommunicationFacade getInstance() {
		if (CommunicationFacade.instance == null) {
			CommunicationFacade.instance = new CommunicationFacade();
		}
		return CommunicationFacade.instance;
	}

	private IDatabaseConnectionFacade databaseConnection;
	private IGreenhouseConnectionFacade greenhouse;

	public CommunicationFacade() {
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

}
