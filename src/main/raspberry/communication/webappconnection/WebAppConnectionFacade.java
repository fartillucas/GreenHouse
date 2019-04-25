package raspberry.communication.webappconnection;

import org.json.JSONObject;
import raspberry.Acquaintance.IWebAppConnectionFacade;

public class WebAppConnectionFacade implements IWebAppConnectionFacade {

    @Override
    public boolean petTheDog(String greenhouseID, String ipAddressPort, int port) {
        JSONObject message = new JSONObject();
        message.put("procedure", "petWatchdog");
        message.put("greenhouseID",greenhouseID);

        return new WatchdogPetter().pet(ipAddressPort, port, message);
    }

    @Override
    public boolean sendIPAddress(int currentPort, String greenhouseID, String ip, int port) {
        JSONObject message = new JSONObject();
        message.put("procedure", "IPAddress");
        message.put("greenhouseID",greenhouseID);
        message.put("port", port);


        return new IPAddressSender().send(ip, port, message);
    }
}
