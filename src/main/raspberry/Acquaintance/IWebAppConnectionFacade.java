package raspberry.Acquaintance;

import java.util.Date;

public interface IWebAppConnectionFacade {


    boolean petTheDog(String greenhouseID, String ipAddressPort, int port);


    boolean sendDatalog(String greenhouseId, Date timeOfReading, double internalTemperature, double extenalTemperature, double humidity, double waterlevel);

    boolean sendIPAddress(int currentPort, String greenhouseID, String ip, int port);

    String startupMessage(int currentPort, String greenhouseID, String ip, int port);
}
