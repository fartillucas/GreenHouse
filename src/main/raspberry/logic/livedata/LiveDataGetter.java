package raspberry.logic.livedata;

import org.json.JSONObject;
import raspberry.Acquaintance.ErrorCode;
import raspberry.Acquaintance.GreenhouseInfoEnum;
import raspberry.Acquaintance.ICurrentMeasurementsFacade;
import raspberry.Acquaintance.ReadableIPAddressPort;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class LiveDataGetter extends Thread {
    private final ICurrentMeasurementsFacade currentMeasurements;
    private boolean itsAGoTime;
    private PrintWriter writer;
    private Scanner scan;
    private String ip;
    private int port;
    private boolean continueRunning;

    public LiveDataGetter(ICurrentMeasurementsFacade currentMeasurementsFacade) {
        this.itsAGoTime = false;
        this.currentMeasurements = currentMeasurementsFacade;
        this.continueRunning = true;
    }

    public ErrorCode setConnection(ReadableIPAddressPort ipAddressPort){
        int[] ipAddress = ipAddressPort.getIpaddress();
        port = ipAddressPort.getPort();

        ip = ipAddress[0] +"."+ ipAddress[1]     +"."+ ipAddress[2] +"."+ ipAddress[3];

        try {
            Socket socket = new Socket(ip, port);
            writer = new PrintWriter(socket.getOutputStream());
            scan = new Scanner(socket.getInputStream());

            JSONObject measurements = new JSONObject("{}");
            measurements.put("procedure","live data");
            measurements.put("greenhouseID", GreenhouseInfoEnum.GREENHOUSEINFO.getName());

            writer.println(measurements.toString());
            writer.flush();
        } catch (IOException e) {
            return ErrorCode.INVALIDIPADDRESS;
        }
        
        itsAGoTime = true;
        return ErrorCode.OK;
    }

    @Override
    public void run() {
        while (!Thread.interrupted() && continueRunning) {
            if (itsAGoTime) {
                Double internalTemp = currentMeasurements.getInternalTemperature();
                Double externalTemp = currentMeasurements.getExternalTemperature();
                Double humidity = currentMeasurements.getHumdity();
                Double waterLevel = currentMeasurements.getWaterlevel();

                JSONObject measurements = new JSONObject("{}");
                measurements.put("procedure","live data");
                measurements.put("greenhouseID", GreenhouseInfoEnum.GREENHOUSEINFO.getName());

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
                continueRunning = false;
            }
        }
    }
}
