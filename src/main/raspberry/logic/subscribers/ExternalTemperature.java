package raspberry.logic.subscribers;

import main.PLCCommunication.Message;
import main.PLCCommunication.PLCConnection;
import raspberry.communication.greenhouseconnection.Connection;

import static main.PLCCommunication.ICommands.*;

public class ExternalTemperature {

	private Message message;
	private PLCConnection connection;


	public double getExternalTemperature() {
		message = new Message(READ_OUTDOOR_TEMP);
		double externalTemperature = 0.0;
		message.setData(); //None data
		connection.addMessage(message);
		if (connection.send())
		{
			if (message.getResultData() != null)
				externalTemperature = (double) (message.getResultData())[0];
			else
				externalTemperature =  18.99; // return a dummy value
		}
		return externalTemperature; //value is returned in celsius

	}
}
