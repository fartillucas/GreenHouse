package raspberry.logic;

import org.json.JSONObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class RaspberryAPI {



    public RaspberryAPI(){
        //TODO get info from interwebs
        //TODO convert to measurements
        //TODO send to schedule

        try(Socket socket = new Socket("localhost",8090);
            Scanner input = new Scanner(socket.getInputStream());
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        ){

            String RaspMessage = "connec\n derp\n";
            System.out.println("Rasp: "+RaspMessage);
            writer.print(RaspMessage);
            writer.flush();

            String reply = "";

            reply+=input.nextLine();

            JSONObject jsonObj = new JSONObject(reply);
            System.out.println("Rasp: JSON"+jsonObj.toString());


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
