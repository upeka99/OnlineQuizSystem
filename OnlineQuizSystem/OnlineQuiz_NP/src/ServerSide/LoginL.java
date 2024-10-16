package ServerSide;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginL extends Thread {

    Socket clientsocket;
    String line;
    int count = 0;
    Statement stmt;
    ResultSet rs;

    public LoginL(Socket clientsocket) {
        this.clientsocket = clientsocket;
    }

    public void input(String line) {
        this.line = line;

    }

    @Override
    public void run() {

        //String file = "C:\\Users\\UPEKa\\Documents\\NetBeansProjects\\Storage\\Login\\loginL.csv";
        String[] message = line.split(",");
        String un = message[0];
        String pw = message[1];
        
        

        String username = null; // initial value of the username
        String password = null;

        try {
            stmt = DBConnection.getStatementConnection();
            rs = stmt.executeQuery("SELECT * FROM loginl WHERE username= '" + un + "'");

            while (rs.next()) {
                username = rs.getString("username"); //assign database login name to the variable
                password = rs.getString("password"); //assign database password to the variable
            }
            //JOptionPane.showMessageDialog(null,username+password , "Successful", JOptionPane.INFORMATION_MESSAGE);
            if (username != null && password != null) {
                if (password.equals(pw)) {
                    OutputStream outs = clientsocket.getOutputStream();
                    PrintWriter write = new PrintWriter(outs, true);
                    write.println("Hello Client! This is the Server...");
                } else {
                    OutputStream outs = clientsocket.getOutputStream();
                    PrintWriter write = new PrintWriter(outs, true);
                    write.println("Please check the password");
                }
            } else {
                OutputStream outs = clientsocket.getOutputStream();
                PrintWriter write = new PrintWriter(outs, true);
                write.println("Please check the username and password");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
