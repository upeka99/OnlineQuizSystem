
package ServerSide;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Score extends Thread{
    
    Socket clientsocket;
    String score;
    String subject;
    String username;
    Statement stmt;
    ResultSet rs;

    public Score(Socket clientsocket) {
        this.clientsocket = clientsocket;
    }

    public void input(String line,String optional) {
        String[] array = line.split(",");
        this.username = array[0];
        this.subject = array[1];
        this.score = optional;
    }

    @Override
    public void run() {

        try {
            stmt = DBConnection.getStatementConnection();

            stmt.executeUpdate("INSERT INTO score(student_name,quiz_id,score) VALUES ('" + username + "', '" + subject + "', '" + score + "')");

        } catch (SQLException e) {
        }

        try {

            OutputStream outs = clientsocket.getOutputStream();
            PrintWriter write = new PrintWriter(outs, true);
            write.println("Score Submitted");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
