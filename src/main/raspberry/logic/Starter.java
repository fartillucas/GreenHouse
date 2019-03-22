package raspberry.logic;

public class Starter {


    public static void main(String[] args) {
        Starter.start();
    }

    public static void start() {
        RaspberryAPI api = new RaspberryAPI();
        api.initialise();
    }
}
