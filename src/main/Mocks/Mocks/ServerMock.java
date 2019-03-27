package Mocks.Mocks;

import org.json.JSONObject;
import raspberry.Acquaintance.ErrorCode;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerMock {

    private static ServerMock instance;

    private ServerSocket serverSocket;
    private boolean success;
    private ErrorCode replyStatus;
    private String lastProceudre = "";
    private Socket liveDataScoket;

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

            lastProceudre = jsonMessage.getString("procedure");

            this.replyStatus = ErrorCode.fromString(response);
            System.out.println(this.getReplyStatus());

            if (response.equals(ErrorCode.OK.toString())){
                this.success =true;
            } else {
                this.success = false;
            }
        } catch (IOException e) {
            replyStatus.equals(ErrorCode.NOTAPPLIED);
            e.printStackTrace();
        }
    }

    public boolean getSuccess() {
        return success;
    }

    public void listenForConnections(){
        System.out.println("Server is listening");
        while (true) {
            try (Socket socket = serverSocket.accept();
                 Scanner input = new Scanner(socket.getInputStream());
                 PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            ) {
                System.out.println("Server: " + input.nextLine());
                if(this.lastProceudre.equals("getLiveData")){
                    liveDataScoket = socket;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public JSONObject readLiveData() throws IOException {
        Scanner scan = new Scanner(liveDataScoket.getInputStream());
        JSONObject measurement = new JSONObject(scan.nextLine());
        return measurement;
    }

    public ErrorCode getReplyStatus() {
        return replyStatus;
    }

    public boolean isDataListenAlive() throws IOException {
        if (this.liveDataScoket != null) {
            PrintWriter writer = new PrintWriter(liveDataScoket.getOutputStream());
            writer.print("IsUAlive?");
        }

        return false;
    }
}
