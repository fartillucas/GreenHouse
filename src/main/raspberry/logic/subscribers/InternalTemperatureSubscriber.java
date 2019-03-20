package raspberry.logic.subscribers;

import main.PLCCommunication.Message;
import main.PLCCommunication.PLCConnection;

import static main.PLCCommunication.ICommands.*;

public class InternalTemperatureSubscriber {

	private PLCConnection connection;
	private Message message;


	public double getIntenalTemperature(){
		message = new Message(READ_GREENHOUSE_TEMP);
		double internalTemperature = 0.0;
		message.setData(); //None data
		connection.addMessage(message);
		if (connection.send())
		{
			if (message.getResultData() != null)
				internalTemperature = (double) (message.getResultData())[0];
			else
				internalTemperature =  19.99; // return a dummy value
		}
		return internalTemperature; // value is returned in celsius
	}

	}



