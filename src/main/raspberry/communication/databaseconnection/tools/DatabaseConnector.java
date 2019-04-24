package raspberry.communication.databaseconnection.tools;
/** Represents an postgressql database connector
 * @author Michael P
 * @param OpenConnection method creates a conneciton
 * to the post gres sql database
 * Inventory Email to the relevant recipient.
 * @param CloseConnection method closes the connection
 * to the postgres sql database
 */
import raspberry.Acquaintance.DatabaseInfoEnum;

import java.sql.*;


public class DatabaseConnector {

    public Connection openConnection() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println(e);
        }

        String url = DatabaseInfoEnum.DATABASEINFO.getInfo();
        String username = DatabaseInfoEnum.DATABASEINFO.getUser();
        String password = DatabaseInfoEnum.DATABASEINFO.getPassword();
        Connection db = null;

        try {
            db = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return db;
    }

    public void closeConnection(Connection st){
        try{
            st.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
