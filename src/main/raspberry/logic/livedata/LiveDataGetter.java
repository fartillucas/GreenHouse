package raspberry.logic.livedata;

import org.json.JSONObject;
import raspberry.Acquaintance.ErrorCode;
import raspberry.Acquaintance.ILiveDataGetter;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import raspberry.logic.currentmeasurements.CurrentMeasurementsFacede;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.BitSet;
import java.util.List;

public class LiveDataGetter implements ILiveDataGetter {
    private boolean itsAGoTime;
    CurrentMeasurementsFacede measurementsFacede = CurrentMeasurementsFacede.getInstance();
    String temp = measurementsFacede.getTemp().toString();
    String temp2 = measurementsFacede.getTemp2().toString();
    String humidity = measurementsFacede.getMoist().toString();
    String water = measurementsFacede.getLevel().toString();
    Socket socket;

    @Override
    public ErrorCode apply(List<String> data) {
        return ErrorCode.NOTAPPLIED;
    }

    public void streamLiveData(String ip, int port) {
            //String  /**JSONMessage**/ = "{\"procedure\":\"streamLiveData\", \"internalTemp\":\"temp\", \"externalTemp\": \"temp2\", \"humidity\": \"humidity\", \"waterLevel\": \"water\"}";

            String JSONMessage = "{\"procedure\":\"streamLiveData\", \"internalTemp\":\"" + temp + "\", \"externalTemp\": \"" + temp2 + "\", \"humidity\": \"humidity\", \"waterLevel\": \"water\"}";
        /*
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.write(JSONMessage);
    */
        System.out.println(JSONMessage);
    }

    public static void main(String[] args) {
        LiveDataGetter liveDataGetter = new LiveDataGetter();
        liveDataGetter.streamLiveData("165",46);
    }

}
