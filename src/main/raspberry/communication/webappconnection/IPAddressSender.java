package raspberry.communication.webappconnection;

import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class IPAddressSender {
    public boolean send(String ip, int port, JSONObject message) {

        try(Socket socket = new Socket(ip, port); PrintWriter writer = new PrintWriter(socket.getOutputStream())) {
            writer.println(message.toString());
        } catch (IOException e) {
            return false;
        }

        return true;
    }
}
