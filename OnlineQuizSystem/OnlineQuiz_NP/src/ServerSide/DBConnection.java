
package ServerSide;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnection {
    static Connection conn;
    static Statement stat = null;

    public static Statement getStatementConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/onlinequizsystem";
            conn = DriverManager.getConnection(url, "root", "");

            stat = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stat;
    }

    public static void closeCon() throws SQLException {
        conn.close();
    }
    
}
