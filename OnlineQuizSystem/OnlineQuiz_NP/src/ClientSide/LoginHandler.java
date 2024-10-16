package ClientSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;

public class LoginHandler {

    String username;
    String password;
    final String address = "localhost";
    final int port = 8080;
    Socket clientsocket;
    String response;
    int code;
    String msg;
    String line;

    public String login(String username, String password, int code) {

        //System.out.println(username+password+code+line+msg);
        if (username.length() == 0 || password.length() == 0) {
            JOptionPane.showMessageDialog(null, "Empty fields", "Error", JOptionPane.ERROR_MESSAGE);

        } else {

            this.username = username;
            this.password = password;
            this.code = code;

            this.line = username + "," + password;
            msg = line + ":" + code + ": test";

            try {
                clientsocket = new Socket(address, port);

                PrintWriter out = new PrintWriter(clientsocket.getOutputStream(), true);
                out.println(msg);

                InputStream in = clientsocket.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                BufferedReader Bfreader = new BufferedReader(reader);
                response = Bfreader.readLine();

                clientsocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if ("Hello Client! This is the Server...".equals(response)) {
            JOptionPane.showMessageDialog(null, "Login Successful", "Successful", JOptionPane.INFORMATION_MESSAGE);

            if (code == 1) {
                Login.getFrames()[0].dispose();
                new LecturerDashboard().setVisible(true);
            } else if (code == 2) {
                Login.getFrames()[0].dispose();
                StudentDashboard sd = new StudentDashboard();
                sd.lbl1.setText(username);
                sd.setVisible(true);
            }
        } else if ("Please check the username and password".equals(response)) {
            JOptionPane.showMessageDialog(null, response, "Error", JOptionPane.ERROR_MESSAGE);
        } else if ("Please check the password".equals(response)) {
            JOptionPane.showMessageDialog(null, response, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Login.getFrames()[0].dispose();
            new Login().setVisible(true);
        }

        return response;
    }
}
