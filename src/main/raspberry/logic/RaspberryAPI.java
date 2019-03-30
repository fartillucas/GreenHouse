package raspberry.logic;

import raspberry.Acquaintance.ErrorCode;
import raspberry.Acquaintance.IInterpreter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class RaspberryAPI {

    private ServerSocket serverSocket;
    private IInterpreter interpreter;
    private boolean continueListening = true;

    public RaspberryAPI(){
        //TODO get info from interwebs
        //TODO convert to measurements
        //TODO send to schedule
        int port = 8091;
        while (this.serverSocket == null) {
            try {
                this.serverSocket = new ServerSocket(port);
            } catch (IOException e) {
                port++;
            }
        }
    }

    public void injectInterpreter(IInterpreter interpreter){
        this.interpreter = interpreter;
    }

    public void initialise(){
        this.sendStartupMessage();
        this.acceptIncomingTraffic();
    }

    private void acceptIncomingTraffic(){
        while (!Thread.interrupted() && continueListening){
            try (Socket socket = serverSocket.accept();
                 Scanner input = new Scanner(socket.getInputStream());
                 PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            ) {
                String message = input.nextLine();

                ErrorCode errorCode = this.interpreter.interpret(message);

                writer.print(errorCode);
                writer.flush();
            } catch (Exception e) {
//                e.printStackTrace();
                System.out.println();
            }
        }
    }

    private void sendStartupMessage(){
        try(Socket socket = new Socket("localhost",8090);
            Scanner input = new Scanner(socket.getInputStream());
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        ){
            String RaspMessage = "connect\n";
            writer.print(RaspMessage);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopListening(){
        continueListening = false;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
