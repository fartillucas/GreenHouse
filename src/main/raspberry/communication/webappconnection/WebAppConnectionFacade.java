package raspberry.communication.webappconnection;

import org.json.JSONObject;
import raspberry.Acquaintance.IWebAppConnectionFacade;
import raspberry.Acquaintance.ServerInfoEnum;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class WebAppConnectionFacade implements IWebAppConnectionFacade {

    @Override
    public boolean petTheDog(String greenhouseID, String ipAddressPort, int port) {
        JSONObject message = new JSONObject();
        message.put("procedure", "petWatchdog");
        message.put("greenhouseID",greenhouseID);

        return new WatchdogPetter().pet(ipAddressPort, port, message);
        //return false;
    }

    @Override
    public boolean sendDatalog(String greenhouseID, Date timeOfReading, Double internalTemperature, Double externalTemperature, Double humidity, Double waterlevel){
        // Desired date format: "Sun, 05 May 2019 14:12:26 GMT"

        String desiredTimeZone = "GMT";
        DateFormat gmtFormat = new SimpleDateFormat();
        TimeZone gmtTime = TimeZone.getTimeZone(desiredTimeZone);
        gmtFormat.setTimeZone(gmtTime);

        String gmtString = gmtFormat.format(timeOfReading);
        String[] gmtSplitString = gmtString.split("[ :]");
        String[] splitTime = timeOfReading.toString().split("[ :]");

        if (gmtSplitString[3].equals("AM")) {
            gmtSplitString[1] = (Integer.parseInt(gmtSplitString[1])+12)+"";
        }

        String convertedTimeString = splitTime[0]+", "+splitTime[2]+" "+splitTime[1]+" "+splitTime[7]+" "+gmtSplitString[1]+":"+splitTime[4]+":"+splitTime[5]+" "+desiredTimeZone;

        JSONObject message = new JSONObject();

        message.put("procedure", "Datalog");
        message.put("greenhouseID",greenhouseID);
        message.put("time of Reading", convertedTimeString);
        message.put("internal temperature",internalTemperature);
        message.put("external temperature", externalTemperature);
        message.put("humidity", humidity);
        message.put("waterlevel", waterlevel);
        message.put("java Date.getTime()",timeOfReading.getTime());

        return new DatalogSender().send(ServerInfoEnum.SERVERINFO.getIP(), ServerInfoEnum.SERVERINFO.getPort(), message);
//        return true;
    }

    @Override
    public boolean sendIPAddress(int currentPort, String greenhouseID, String ip, int port) {
        JSONObject message = new JSONObject();
        message.put("procedure", "IPAddress");
        message.put("greenhouseID",greenhouseID);
        message.put("port", currentPort);

        return new IPAddressSender().send(ip, port, message);
//        return true;
    }

    @Override
    public String startupMessage(int currentPort, String greenhouseID, String ip, int port) {
        JSONObject message = new JSONObject();
        message.put("procedure", "Startup");
        message.put("port", currentPort);
        message.put("greenhouseID", greenhouseID);

        return new StartupMessageSender().send(ip, port, message);
//        return new JSONObject().toString();
    }
}
