package raspberry.Acquaintance;

public enum ErrorCode {

    OK("OK","OK"),
    NOTAPPLIED("NOTAPPLIED","Can't apply"),
    WRONGFORMAT("WRONGFORMAT","JSON file content doesn't match procedure type"),
    INVALIDIPADDRESS("INVALIDIADDRESS", "The IPAddress or port is out of bounce"),
    UNDEFINEDPROCEDURE("UNDEFINEDPROCEDURE","Unknown procedure"),
    TEST("TEST1","TEST2");


    private String message;
    private String name;

    ErrorCode(String name, String message){
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return this.message;
    }

    public static ErrorCode fromString(String name) {
        for (ErrorCode errodcode : ErrorCode.values()) {
            if (errodcode.name.equalsIgnoreCase(name)) {
                return errodcode;
            }
        }
        return null;
    }
    public void setName(String name){
        this.name = name;
    }


}
