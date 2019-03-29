package raspberry.logic.livedata;

import org.json.JSONObject;
import raspberry.Acquaintance.ErrorCode;
import raspberry.Acquaintance.ICurrentMeasurements;
import raspberry.Acquaintance.ReadableIPAddressPort;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class LiveDataGetter extends Thread {
    private final ICurrentMeasurements currentMeasurements;
    private boolean itsAGoTime;
    private PrintWriter writer;
    private Scanner scan;
    private String ip;
    private int port;

    public LiveDataGetter(ICurrentMeasurements currentMeasurementsFacade) {
        this.itsAGoTime = false;
        this.currentMeasurements = currentMeasurementsFacade;
    }

    public ErrorCode setConnection(ReadableIPAddressPort ipAddressPort){
        int[] ipAddress = ipAddressPort.getIpaddress();
        port = ipAddressPort.getPort();

        ip = ipAddress[0] +"."+ ipAddress[1]     +"."+ ipAddress[2] +"."+ ipAddress[3];

        try {
            Socket socket = new Socket(ip, port);
            writer = new PrintWriter(socket.getOutputStream());
            scan = new Scanner(socket.getInputStream());

            writer.print("LiveDate ready\n");
        } catch (IOException e) {
            return ErrorCode.INVALIDIPADDRESS;
        }
        
        itsAGoTime = true;
        return ErrorCode.OK;
    }

    @Override
    public void run() {
        while (true) {
            if (itsAGoTime) {
                Double internalTemp = currentMeasurements.getTemp();
                Double externalTemp = currentMeasurements.getTemp2();
                Double humidity = currentMeasurements.getMoist();
                Double waterLevel = currentMeasurements.getLevel();

                JSONObject measurements = new JSONObject("{}");

                measurements.put("internal temperature", internalTemp);
                measurements.put("external temperature", externalTemp);
                measurements.put("humidity", humidity);
                measurements.put("water level", waterLevel);

                if (internalTemp == null) {
                    measurements.put("internal temperature", JSONObject.NULL);
                }

                if (externalTemp == null) {
                    measurements.put("external temperature", JSONObject.NULL);
                }

                if (humidity == null) {
                    measurements.put("humidity", JSONObject.NULL);
                }

                if (waterLevel == null) {
                    measurements.put("water level", JSONObject.NULL);
                }

                String message = measurements.toString();

                writer.print(message+"\n");
                writer.flush();
//                if (scan.hasNextLine()) {
//                    String quit = scan.nextLine();
//                    if (quit.equalsIgnoreCase("stop")) {
//                        itsAGoTime = false;
//                    }
//                }
            }

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
