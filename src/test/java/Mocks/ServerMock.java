package Mocks;

import org.json.JSONObject;
import raspberry.Acquaintance.ErrorCode;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class ServerMock {

    private static ServerMock instance;

    private List<Socket> sockets;
    private ServerSocket serverSocket;
    private ErrorCode replyStatus;

    public static ServerMock getInstance(){
        if (instance == null){
            instance = new ServerMock();
        }

        return instance;
    }

    private ServerMock(){
        try {
            this.serverSocket = new ServerSocket(8090);
            Socket socket = serverSocket.accept();
            this.sockets.add(socket);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendMessage(String message, int socketNumber){

        Socket socket = this.sockets.get(socketNumber);

        try (Scanner input = new Scanner(socket.getInputStream());
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);){

            System.out.println("Server: "+input.nextLine()+input.nextLine());


            JSONObject jsonObj = new JSONObject(message);

            System.out.println("Server: JSON is "+jsonObj.toString());

            writer.print(jsonObj.toString()+"\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
