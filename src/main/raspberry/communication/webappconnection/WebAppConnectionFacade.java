package raspberry.communication.webappconnection;

import org.json.JSONObject;
import raspberry.Acquaintance.ReadableIPAddressPort;
import raspberry.Acquaintance.IWebAppConnectionFacade;

public class WebAppConnectionFacade implements IWebAppConnectionFacade {


    @Override
    public boolean petTheDog(String greenhouseID, String ipAddressPort, int port) {
        JSONObject message = new JSONObject("{}");
        message.append("call", "petWatchdog"); //TODO streamline how to hit the right thingy
        message.append("greenhouseID",greenhouseID);

        return new WatchdogPetter().pet(ipAddressPort, port, message);
    }
}
