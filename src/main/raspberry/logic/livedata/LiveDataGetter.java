package raspberry.logic.livedata;

import org.json.JSONObject;
import raspberry.Acquaintance.ErrorCode;
import raspberry.Acquaintance.ILiveDataGetter;
import raspberry.logic.IPAddressPort;
import raspberry.logic.currentmeasurements.CurrentMeasurementsFacede;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class LiveDataGetter extends Thread implements ILiveDataGetter {
    private boolean itsAGoTime;
    private PrintWriter writer;
    private Scanner scan;
    private String ip;
    private int port;

    public void streamLiveData() {
    }

    public LiveDataGetter() {
        this.itsAGoTime = false;
    }

    public ErrorCode setConnection(IPAddressPort ipAddressPort){
        int[] ipAddress = ipAddressPort.getIpaddress();
        port = ipAddressPort.getPort();

        ip = ipAddress[0] +"."+ ipAddress[1]     +"."+ ipAddress[2] +"."+ ipAddress[3];

        try {
            Socket socket = new Socket(ip, port);
            writer = new PrintWriter(socket.getOutputStream());
            scan = new Scanner(socket.getInputStream());

            writer.print("LiveDate ready\n");
            System.out.println("Datagetter: Connection set");
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
                Double internalTemp = CurrentMeasurementsFacede.getInstance().getTemp();
                Double externalTemp = CurrentMeasurementsFacede.getInstance().getTemp2();
                Double humidity = CurrentMeasurementsFacede.getInstance().getMoist();
                Double waterLevel = CurrentMeasurementsFacede.getInstance().getLevel();

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
                System.out.println("\nDatagetter: "+message);

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
