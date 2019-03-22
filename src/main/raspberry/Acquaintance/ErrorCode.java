package raspberry.Acquaintance;

public enum ErrorCode {

    OK("OK"),
    NOTAPPLIED("Can't apply"),
    WRONGFORMAT("JSON file content doesn't match procedure type"),
    UNDEFINEDPROCEDURE("Unknown procedure");

    private String message;

    ErrorCode(String message){
        this.message = message;
    }


}
