package raspberry.communication.webappconnection;

import org.json.JSONObject;
import raspberry.Acquaintance.IWebAppConnectionFacade;
import raspberry.Acquaintance.ServerInfoEnum;

import java.util.Date;

public class WebAppConnectionFacade implements IWebAppConnectionFacade {

    @Override
    public boolean petTheDog(String greenhouseID, String ipAddressPort, int port) {
        JSONObject message = new JSONObject();
        message.put("procedure", "petWatchdog");
        message.put("greenhouseID",greenhouseID);

        //return new WatchdogPetter().pet(ipAddressPort, port, message);
        return true;
    }

    @Override
    public boolean sendDatalog(String greenhouseID, Date timeOfReading, double internalTemperature, double extenalTemperature, double humidity, double waterlevel){
        JSONObject message = new JSONObject();
        message.put("procedure", "Datalog");
        message.put("greenhouseID",greenhouseID);
        message.put("time of Reading", timeOfReading.getTime());
        message.put("internal temperature",internalTemperature);
        message.put("extenal temperature", extenalTemperature);
        message.put("humidity", humidity);
        message.put("waterlevel", waterlevel);

        //return new IPAddressSender().send(ServerInfoEnum.SERVERINFO.getIP(), ServerInfoEnum.SERVERINFO.getPort(), message);
        return true;
    }

    @Override
    public boolean sendIPAddress(int currentPort, String greenhouseID, String ip, int port) {
        JSONObject message = new JSONObject();
        message.put("procedure", "IPAddress");
        message.put("greenhouseID",greenhouseID);
        message.put("port", currentPort);

//        return new IPAddressSender().send(ip, port, message);
        return true;
    }

    @Override
    public String startupMessage(int currentPort, String greenhouseID, String ip, int port) {
        JSONObject message = new JSONObject();
        message.put("procedure", "Startup");
        message.put("port", currentPort);
        message.put("greenhouseID", greenhouseID);

        return new StartupMessageSender().send(ip, port, message);
    }
}
