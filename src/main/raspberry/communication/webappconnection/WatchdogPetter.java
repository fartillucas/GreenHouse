package raspberry.communication.webappconnection;

import org.json.JSONObject;
import raspberry.Acquaintance.ReadableIPAddressPort;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class WatchdogPetter {
    public boolean pet(String ipAddressPort, int port, JSONObject message) {

        try(Socket socket = new Socket(ipAddressPort, port); PrintWriter writer = new PrintWriter(socket.getOutputStream())) {

            writer.println(message.toString());
        } catch (IOException e) {
            return false;
        }

        return true;
    }
}
