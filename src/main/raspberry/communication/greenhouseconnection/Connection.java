package raspberry.communication.greenhouseconnection;

import main.Greenhouse;
import main.IGreenhouse;
import main.PLCCommunication.Message;
import main.PLCCommunication.PLCConnection;
import main.PLCCommunication.UDPConnection;

 public class Connection {

	public PLCConnection GreenhouseConnection() {

		PLCConnection connection = new UDPConnection(5000, "192.168.0.40");
		IGreenhouse api = new Greenhouse(connection);

		return connection;
	}
}






