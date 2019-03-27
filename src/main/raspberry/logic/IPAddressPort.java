package raspberry.logic;

public class IPAddressPort {

    int[] ipaddress;
    int port;

    public IPAddressPort(int[] ipaddress, int port) {
        this.ipaddress = ipaddress;
        this.port = port;
    }

    public int[] getIpaddress() {
        return ipaddress;
    }

    public int getPort() {
        return port;
    }
}


