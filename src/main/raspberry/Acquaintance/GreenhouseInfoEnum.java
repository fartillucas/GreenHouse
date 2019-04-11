package raspberry.Acquaintance;

public enum GreenhouseInfoEnum {

    GREENHOUSEINFO("standardGreenhouse"); //TODO replace other places to use this

    private String name;

    GreenhouseInfoEnum(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

}
