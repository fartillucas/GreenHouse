package raspberry.logic;

public class Starter {


    public static void main(String[] args) {
        Starter.start();
    }

    public static void start() {
        System.out.println("this is here");
        RaspberryAPI api = new RaspberryAPI();
    }
}
