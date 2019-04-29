package raspberry.logic;

import jdk.internal.org.objectweb.asm.tree.analysis.Interpreter;
import org.json.JSONObject;
import raspberry.Acquaintance.ErrorCode;
import raspberry.Acquaintance.GreenhouseInfoEnum;
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
    private String serverIP;
    private int serverPort;

    public RaspberryAPI(){
        int port = 8091;
        serverIP = "localhost";
        serverPort = 8090;

        while (this.serverSocket == null) {
            try {
                this.serverSocket = new ServerSocket(port);
            } catch (IOException e) {
                port++;
            }
        }

        OutFacadeLogic.getInstance().setCurrentServerPort(port);
    }

    public void injectInterpreter(IInterpreter interpreter){
        this.interpreter = interpreter;
    }

    public void initialise(){
        this.sendStartupMessage();
        this.acceptIncomingTraffic();
        OutFacadeLogic.getInstance().setServerInfo(serverIP,serverPort);
    }

    private void acceptIncomingTraffic(){
        while (!Thread.interrupted() && continueListening){
            try (Socket socket = serverSocket.accept();
                 Scanner input = new Scanner(socket.getInputStream());
                 PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            ) {
                String message = input.nextLine();

                ErrorCode errorCode = this.interpreter.interpret(message);

                JSONObject answer = new JSONObject();

                answer.put("procedure", "errorcode");
                answer.put("errorcode", errorCode.getName());

                writer.print(answer.toString()+"\n");
                writer.flush();
            } catch (Exception e) {
//                e.printStackTrace();
                System.out.println();
            }
        }
    }

    private void sendStartupMessage(){
        try{
            String schedule = OutFacadeLogic.getInstance().startupMessage();
            interpreter.interpret(schedule);
        } catch (Exception e){
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
