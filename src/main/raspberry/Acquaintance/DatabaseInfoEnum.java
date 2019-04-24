package raspberry.Acquaintance;

import raspberry.communication.databaseconnection.tools.DatabaseConnector;
import raspberry.logic.datalogger.DataloggerFacade;

public enum  DatabaseInfoEnum {

    DATABASEINFO("jdbc:mysql://10.123.3.54:3306/greenhousedatabase", "poweruser", "Admin");


    private String info;
    private String user;
    private String password;

    DatabaseInfoEnum(String info, String user, String password){
        this.info = info;
        this.user = user;
        this.password = password;
    }

    public String getInfo(){
        return info;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
