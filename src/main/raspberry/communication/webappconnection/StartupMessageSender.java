package raspberry.communication.webappconnection;

import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class StartupMessageSender {

    public String send(String ip, int port, JSONObject message) {

        try(Socket socket = new Socket(ip, port);
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            Scanner input = new Scanner(socket.getInputStream())) {

            writer.println(message.toString());
            writer.flush();
            String schedule = input.nextLine();
            return schedule;
        } catch (IOException e) {
             return "";
        }
    }
}
