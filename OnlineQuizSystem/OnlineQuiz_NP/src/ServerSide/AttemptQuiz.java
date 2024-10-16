package ServerSide;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONObject;

public class AttemptQuiz extends Thread {

    Socket clientsocket;
    String line;
    String qid;
    String response;
    Statement stmt;
    ResultSet rs;

    public AttemptQuiz(Socket clientsocket) {
        this.clientsocket = clientsocket;
    }

    public void input(String qid) {

        this.qid = qid;
        //JOptionPane.showMessageDialog(null, qid, "Successful", JOptionPane.INFORMATION_MESSAGE);

    }

    @Override
    public void run() {

        try {
            stmt = DBConnection.getStatementConnection();
            rs = stmt.executeQuery("SELECT question,answer1,answer2,answer3,answer4,correctanswer FROM quizes WHERE quiz_id = '" + qid + "'");

            //JOptionPane.showMessageDialog(null, rs, "Successful", JOptionPane.INFORMATION_MESSAGE);
            JSONArray jsonArray = resultSetToJsonArray(rs);

            // Get the output stream to send data
            OutputStream outputStream = clientsocket.getOutputStream();

            // Convert JSON data to string
            String jsonString = jsonArray.toString();

            // Send JSON data through the output stream
            PrintWriter out = new PrintWriter(outputStream, true);
            out.println(jsonString);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
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
