package Mocks;

import org.json.JSONObject;
import raspberry.Acquaintance.ErrorCode;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServerMock {

    private static ServerMock instance;

    private ServerSocket serverSocket;
    private boolean replyStatus;

    public static ServerMock getInstance() throws IOException {
        if (instance == null){
            instance = new ServerMock();
        }
        System.out.println("Server: getting instance");
        return instance;
    }

    private ServerMock() throws IOException {
        this.serverSocket = new ServerSocket(8090);
    }

    public void sendMessage(String message, int socketNumber){

        try (Socket socket = new Socket("localhost",8091);
             Scanner input = new Scanner(socket.getInputStream());
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        ){
            JSONObject jsonMessage = new JSONObject(message);

            writer.print(jsonMessage.toString()+"\n");
            writer.flush();

            String response = input.nextLine();
            System.out.println("Server response: "+response);
            System.out.println(ErrorCode.OK.toString());

            if (response.equals(ErrorCode.OK.toString())){
                this.replyStatus=true;
            } else {
                this.replyStatus = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean getReplyStatus() {
        return replyStatus;
    }

    public void listenForConnections(){
        System.out.println("Server is listening");
        while (true) {
            try (Socket socket = serverSocket.accept();
                 Scanner input = new Scanner(socket.getInputStream());
                 PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            ) {
                System.out.println("Server: " + input.nextLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
