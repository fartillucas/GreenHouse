package raspberry.Acquaintance;

public enum ErrorCode {

    OK("OK"),
    NOTAPPLIED("can't apply"),
    UNDEFINEDPROCEDURE("Unknown procedure");

    private String message;

    ErrorCode(String message){
        this.message = message;
    }


}
