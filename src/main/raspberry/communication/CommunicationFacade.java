package raspberry.communication;

import raspberry.Acquaintance.ICommunicationsFacade;
import raspberry.communication.communicationAquaintance.IGreenhouseConnectionFacade;
import raspberry.communication.greenhouseconnection.GreenhouseConnectionFacade;

public class CommunicationFacade implements ICommunicationsFacade {

	private static CommunicationFacade instance;

	private IGreenhouseConnectionFacade greenhouse;

	public static CommunicationFacade getInstance() {
		if (CommunicationFacade.instance == null) {
			CommunicationFacade.instance = new CommunicationFacade();
		}
		return CommunicationFacade.instance;
	}

	private CommunicationFacade() {
		this.greenhouse = new GreenhouseConnectionFacade();
	}

	@Override
	public IGreenhouseConnectionFacade getGreenhouseConnection() {
		return this.greenhouse;
	}

}
