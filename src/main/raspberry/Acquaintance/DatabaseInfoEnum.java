package raspberry.Acquaintance;

import raspberry.communication.databaseconnection.tools.DatabaseConnector;
import raspberry.logic.datalogger.DataloggerFacade;

public enum  DatabaseInfoEnum {

    DATABASEINFO("hejsa"); //TODO fill in with actual info


    private String info;

    DatabaseInfoEnum(String info){
        this.info = info;
    }

    public String getInfo(){
        return info;
    }

}
