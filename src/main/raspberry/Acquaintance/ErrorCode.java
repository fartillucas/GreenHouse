package raspberry.Acquaintance;

public enum ErrorCode {

    OK("success"),
    NOTAPPLIED("can't apply");

    private String message;

    ErrorCode(String message){
        this.message = message;
    }

}
