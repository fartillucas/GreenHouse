package raspberry.communication.databaseconnection.tools;
/** Represents an postgressql database connector
 * @author Michael P
 * @param OpenConnection method creates a conneciton
 * to the post gres sql database
 * Inventory Email to the relevant recipient.
 * @param CloseConnection method closes the connection
 * to the postgres sql database
 */
import java.sql.*;


public class DatabaseConnector {

    public Connection openConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println(e);
        }

        String url = ""; //TODO connect to the database
        String username = "si3_2018_group_21";
        String password = "grim26:bijou";
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
