package raspberry.communication;

import raspberry.Acquaintance.ICommunicationsFacade;
import raspberry.communication.greenhouseconnection.GreenhouseConnectionFacade;

import java.util.BitSet;

public class CommunicationFacade implements ICommunicationsFacade {
	private static CommunicationFacade instance;
	private GreenhouseConnectionFacade greenhouse;


	public static CommunicationFacade getInstance() {
		if (CommunicationFacade.instance == null) {
			CommunicationFacade.instance = new CommunicationFacade();
		}
		return CommunicationFacade.instance;
	}

	private CommunicationFacade() {
		this.greenhouse = new GreenhouseConnectionFacade();
	}

	public GreenhouseConnectionFacade getGreenhouseConnection() {
		return this.greenhouse;
	}


}
