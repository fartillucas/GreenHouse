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
    private Thread acceptingSocketsThread;

    private ServerSocket serverSocket;
    private boolean success;
    private ErrorCode replyStatus;
    private String lastProcedure = "";
    private Socket liveDataScoket;
    private PrintWriter liveWriter;
    private Scanner liveScanner;
    private boolean recievedPetting;
    private boolean recievedIPAddress;

    public static ServerMock getInstance() throws IOException {
        if (instance == null){
            instance = new ServerMock();
        }
        return instance;
    }

    private ServerMock() throws IOException {
        this.serverSocket = new ServerSocket(8090);
        this.recievedPetting=false;
        this.recievedIPAddress=false;
    }

    public ServerMock(int port) throws IOException {
//        port = port; // static
        this.serverSocket = new ServerSocket(port);

        acceptingSocketsThread = new Thread(() -> {
            while (!Thread.interrupted()) {
                try {
                    Socket socket = serverSocket.accept();
                    Scanner input = new Scanner(socket.getInputStream());
                    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

                    String openingMessage = input.nextLine();
                    JSONObject message = null;
                    try {

                        message = new JSONObject(openingMessage);
                    } catch (Exception e){
                        e.printStackTrace();

                    }

                    switch (message.getString("procedure")){
                        case "petWatchdog":
                            recievedPetting = true;
                            break;
                        case "IPAddress":
                            recievedIPAddress = true;
                            break;
                        case "Startup":
                            writer.println(new JSONObject());
                            writer.flush();
                            break;
                        case "live data":
                            liveDataScoket = socket;
                            liveScanner = input;
                            liveWriter = writer;
                            break;
                        default:
                    }

                    if(this.lastProcedure.equals("getLiveData")){

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        acceptingSocketsThread.setName("ServerMock");
        acceptingSocketsThread.setDaemon(true);
        acceptingSocketsThread.start();
    }

    public void sendMessage(String message, int socketNumber){

        try (Socket socket = new Socket("localhost",8091);
             Scanner input = new Scanner(socket.getInputStream());
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        ){
            JSONObject jsonMessage = new JSONObject(message);
            lastProcedure = jsonMessage.getString("procedure");
            writer.print(jsonMessage.toString()+"\n");
            writer.flush();

            String response = input.nextLine();

            JSONObject jsonResponse = new JSONObject(response);
            String error = jsonResponse.getString("errorcode");

            this.replyStatus = ErrorCode.fromString(error);

            if (error.equals(ErrorCode.OK.toString())){
                this.success =true;
            } else {
                this.success = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean getSuccess() {
        return success;
    }

    public JSONObject readLiveData() throws IOException {
        Scanner scan = new Scanner(liveDataScoket.getInputStream());

        String message = scan.nextLine();
        JSONObject measurement = new JSONObject(message);

        return measurement;
    }

    public ErrorCode getReplyStatus() {
        return replyStatus;
    }

    public boolean isDataListenAlive() {

        if (this.liveDataScoket != null) {
            try {
                PrintWriter writer  = new PrintWriter(liveDataScoket.getOutputStream());
                writer.print("IsUAlive?");
            } catch (IOException e) {
                return false;
            }
            return true;
        }

        return false;
    }

    public void stopThreads() {
        try {
            this.serverSocket.close();
            this.acceptingSocketsThread.interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public boolean recievedPetting() {
        return this.recievedPetting;
    }

    public boolean recievedIPAdress() {
        return this.recievedIPAddress;
    }
}
