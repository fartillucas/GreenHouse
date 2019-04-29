package raspberry.Acquaintance;

public interface IWebAppConnectionFacade {


    boolean petTheDog(String greenhouseID, String ipAddressPort, int port);

    boolean sendIPAddress(int currentPort, String greenhouseID, String ip, int port);

    String startupMessage(int currentPort, String greenhouseID, String ip, int port);
}
