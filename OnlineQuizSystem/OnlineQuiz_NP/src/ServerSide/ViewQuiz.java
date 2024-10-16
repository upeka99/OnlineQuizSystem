package ServerSide;

import static ServerSide.AttemptQuiz.resultSetToJsonArray;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class ViewQuiz extends Thread {

    Socket clientsocket;
    String score;
    String subject;
    String username;
    Statement stmt;
    ResultSet rs;

    public ViewQuiz(Socket clientsocket) {
        this.clientsocket = clientsocket;
    }

    public void input(String optional) {
        this.subject = optional;

    }

    @Override
    public void run() {

        try {
            stmt = DBConnection.getStatementConnection();
            String search = subject+"%";

            rs = stmt.executeQuery("SELECT * FROM quizes WHERE quiz_id LIKE '" + search + "';");

            JSONArray jsonArray = resultSetToJsonArray(rs);

            OutputStream outputStream = clientsocket.getOutputStream();

            String jsonString = jsonArray.toString();

            PrintWriter out = new PrintWriter(outputStream, true);
            out.println(jsonString);

        } catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception ex) {
            Logger.getLogger(AttemptQuiz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static JSONArray resultSetToJsonArray(ResultSet resultSet) throws Exception {

        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()) {
            JSONObject jsonObject = new JSONObject();
            int columnCount = resultSet.getMetaData().getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                String columnName = resultSet.getMetaData().getColumnName(i);
                Object columnValue = resultSet.getObject(i);
                jsonObject.put(columnName, columnValue);
            }
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }
    
    
}
