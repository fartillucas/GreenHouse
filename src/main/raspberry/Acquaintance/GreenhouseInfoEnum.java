package raspberry.Acquaintance;

public enum GreenhouseInfoEnum {

    GREENHOUSEINFO("standardGreenhouse", "localhost", 8081); //TODO replace other places to use this

    private String name;
    private String ip;
    private int port;

    GreenhouseInfoEnum(String name, String ip, int port){
        this.name = name;
        this.ip = ip;
        this.port = port;
    }

    public String getName(){
        return name;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }
}
