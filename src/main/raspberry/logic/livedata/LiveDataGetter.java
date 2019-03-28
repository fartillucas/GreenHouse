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



    public void streamLiveData() {

    }

    public LiveDataGetter() {
        this.itsAGoTime = false;
    }

    public ErrorCode setConnection(IPAddressPort ipAddressPort){
        int[] ipAddress = ipAddressPort.getIpaddress();
        int port = ipAddressPort.getPort();

        String ip = ipAddress[0] +"."+ ipAddress[1] +"."+ ipAddress[2] +"."+ ipAddress[3];

        try {
            Socket socket = new Socket(ip, port);
            writer = new PrintWriter(socket.getOutputStream());
            scan = new Scanner(socket.getInputStream());
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

                JSONObject measurements = new JSONObject();
                Double internalTemp = CurrentMeasurementsFacede.getInstance().getTemp();
                Double externalTemp = CurrentMeasurementsFacede.getInstance().getTemp2();
                Double humidity = CurrentMeasurementsFacede.getInstance().getMoist();
                Double waterLevel = CurrentMeasurementsFacede.getInstance().getLevel();
                measurements.put("internal temperature", internalTemp);
                measurements.put("external temperature", externalTemp);
                measurements.put("humidity", humidity);
                measurements.put("water level", waterLevel);

                writer.print(measurements + "\n");
                writer.flush();
                if (scan.hasNextLine()) {
                    String quit = scan.nextLine();
                    if (quit.equalsIgnoreCase("stop")) {
                        itsAGoTime = false;
                    }
                }
            }

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
