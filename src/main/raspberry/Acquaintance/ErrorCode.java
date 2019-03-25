package raspberry.Acquaintance;

public enum ErrorCode {

    OK("OK","OK"),
    NOTAPPLIED("NOTAPPLIED","Can't apply"),
    WRONGFORMAT("WRONGFORMAT","JSON file content doesn't match procedure type"),
    UNDEFINEDPROCEDURE("UNDEFINEDPROCEDURE","Unknown procedure");

    private String message;
    private String name;

    ErrorCode(String name, String message){
        this.message = message;
        this.name = name;
    }

    public String getMessage() {
        return this.message;
    }

    public String getName() {
        return name;
    }

    public static ErrorCode fromString(String name) {
        for (ErrorCode errodcode : ErrorCode.values()) {
            if (errodcode.name.equalsIgnoreCase(name)) {
                return errodcode;
            }
        }
        return null;
    }


}
