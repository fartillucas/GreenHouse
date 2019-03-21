package raspberry.logic;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class RaspberryAPI {



    public RaspberryAPI(){
        //TODO get info from interwebs
        //TODO convert to measurements
        //TODO send to schedule

//        Socket socket = new Socket("localhost", 8081)

        try (ServerSocket serverSocket = new ServerSocket(8080)){

            System.out.println("here i am");
            Socket socket = serverSocket.accept();

            Scanner input = new Scanner(socket.getInputStream());

            String test = "";

            while (input.hasNext()){
                test+=input.nextLine();
            }

            System.out.println(test);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
