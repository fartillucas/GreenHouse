package raspberry.Acquaintance;

public enum ServerInfoEnum {
    SERVERINFO("localhost", 8090),
    MOCKSERVERINFO("localhost", 8090);

    ServerInfoEnum(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    private String ip;
    private int port;

    public String getIP(){
        return ip;
    }
    public int getPort(){
        return port;
    }

}
