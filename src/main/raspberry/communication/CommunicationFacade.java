package raspberry.communication;

import raspberry.communication.greenhouseconnection.GreenhouseConnectionFacade;

import java.util.BitSet;

public class CommunicationFacade {
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


}
