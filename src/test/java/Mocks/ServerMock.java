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
    private boolean replyStatus;

    public static ServerMock getInstance(){
        if (instance == null){
            instance = new ServerMock();
        }

        return instance;
    }

    private ServerMock(){

        Scanner input = null;

        try {
            this.serverSocket = new ServerSocket(8090);
            Socket socket = serverSocket.accept();

            this.sockets.add(socket);

            input = new Scanner(socket.getInputStream());
            System.out.println("Server: "+input.nextLine());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if  (input!=null){
                input.close();
            }
        }
    }

    public void sendMessage(String procedure, String message, int socketNumber){

        Socket socket = this.sockets.get(socketNumber);
        Boolean success = false;

        try (Scanner input = new Scanner(socket.getInputStream());
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);){

            JSONObject jsonObj = new JSONObject(message);

            System.out.println(procedure+"\n"+jsonObj.toString()+"\n");

            writer.print(procedure+"\n"+jsonObj.toString()+"\n");
            writer.flush();

            String status = input.nextLine();

            if (status.equals("OK")){
                success = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.replyStatus = success;
    }

    public boolean getReplyStatus() {
        return replyStatus;
    }
}
