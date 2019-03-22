package raspberry.logic;

import raspberry.Acquaintance.ErrorCode;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class RaspberryAPI {

    private ServerSocket serverSocket;
    private ProcedureInterpreter interpreter;

    public RaspberryAPI(){
        //TODO get info from interwebs
        //TODO convert to measurements
        //TODO send to schedule
        try {
            this.serverSocket = new ServerSocket(8091);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.interpreter = new ProcedureInterpreter();
    }

    public void initialise(){
        this.sendStartupMessage();
        this.acceptIncomingTraffic();
    }

    private void acceptIncomingTraffic(){
        while (true){
            try (Socket socket = serverSocket.accept();
                 Scanner input = new Scanner(socket.getInputStream());
                 PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            ) {
                String message = input.nextLine();
                System.out.println(message);

                ErrorCode errorCode = this.interpreter.interpret(message);

                writer.print(errorCode);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendStartupMessage(){
        try(Socket socket = new Socket("localhost",8090);
            Scanner input = new Scanner(socket.getInputStream());
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        ){
            String RaspMessage = "connect Raspberry 01\n";
            System.out.println("Rasp: "+RaspMessage);
            writer.print(RaspMessage);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
