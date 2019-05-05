package raspberry.Acquaintance;

import java.util.Date;

public interface IWebAppConnectionFacade {


    boolean petTheDog(String greenhouseID, String ipAddressPort, int port);


    boolean sendDatalog(String greenhouseId, Date timeOfReading, Double internalTemperature, Double extenalTemperature, Double humidity, Double waterlevel);

    boolean sendIPAddress(int currentPort, String greenhouseID, String ip, int port);

    String startupMessage(int currentPort, String greenhouseID, String ip, int port);
}
