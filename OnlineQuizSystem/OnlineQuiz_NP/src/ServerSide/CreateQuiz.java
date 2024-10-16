package ServerSide;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class CreateQuiz extends Thread {

    Socket clientsocket;
    String line;
    String subject;
    Statement stmt;
    ResultSet rs;

    public CreateQuiz(Socket clientsocket) {
        this.clientsocket = clientsocket;
    }

    public void input(String line, String optional) {
        this.line = line;
        this.subject = optional;
    }

    @Override
    public void run() {

        String[] input = line.split(",");
        String q = input[0];
        String a1 = input[1];
        String a2 = input[2];
        String a3 = input[3];
        String a4 = input[4];
        String ca = input[5];

        //JOptionPane.showMessageDialog(null, line+" "+subject , "Successful", JOptionPane.INFORMATION_MESSAGE);
        try {
            stmt = DBConnection.getStatementConnection();

            stmt.executeUpdate("INSERT INTO quizes VALUES ('" + subject + "', '" + q + "', '" + a1 + "',  '" + a2 + "', '" + a3 + "', '" + a4 + "', '" + ca + "')");

        } catch (SQLException e) {
        }

        try {

            OutputStream outs = clientsocket.getOutputStream();
            PrintWriter write = new PrintWriter(outs, true);
            write.println("Question Added");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
