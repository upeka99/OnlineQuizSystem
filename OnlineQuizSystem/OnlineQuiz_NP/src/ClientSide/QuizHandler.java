package ClientSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;

public class QuizHandler {

    int code;
    String response;
    final String address = "localhost";
    final int port = 8080;
    Socket clientsocket;
    String subject;

    public void input(String subject) {
        this.subject = subject;
    }

    public void create(String q, String a1, String a2, String a3, String a4, String ca, String qid, int c, String subject) {

        if (q.length() == 0 || a1.length() == 0 || a2.length() == 0 || a3.length() == 0 || a4.length() == 0 || ca == null || qid.length() == 0) {
            JOptionPane.showMessageDialog(null, "Empty fields", "Error", JOptionPane.ERROR_MESSAGE);

        } else {

            String Q = q;
            String A1 = a1;
            String A2 = a2;
            String A3 = a3;
            String A4 = a4;
            String CA = ca;
            String QID = qid;

            //String subject = s;
            this.code = c;

            String line = Q + "," + A1 + "," + A2 + "," + A3 + "," + A4 + "," + CA;
            String quiz = subject + "_" + qid;
            String msg = line + ":" + code + ":" + quiz;

            //JOptionPane.showMessageDialog(null, msg, "Successful", JOptionPane.INFORMATION_MESSAGE);
            try {
                clientsocket = new Socket(address, port);

                PrintWriter out = new PrintWriter(clientsocket.getOutputStream(), true);
                out.println(msg);

                InputStream in = clientsocket.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                BufferedReader Bfreader = new BufferedReader(reader);
                response = Bfreader.readLine();

                JOptionPane.showMessageDialog(null, response, "Successful", JOptionPane.INFORMATION_MESSAGE);

                clientsocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String attempt(String subject, String qno, int code) {

        //ResultSet receivedResultSet = null;
        String jsonString = null;
        String response = "[]";

        if (subject.length() == 0 || qno.length() == 0) {
            JOptionPane.showMessageDialog(null, "Empty fields", "Error", JOptionPane.ERROR_MESSAGE);

        } else {
            this.code = code;
        }
        String quiz = subject + "_" + qno;
        String msg = "test :" + code + ":" + quiz;

        try {
            clientsocket = new Socket(address, port);

            PrintWriter out = new PrintWriter(clientsocket.getOutputStream(), true);
            out.println(msg);

            InputStream inputStream = clientsocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            jsonString = reader.readLine();

            if (jsonString.equals(response)) {
                JOptionPane.showMessageDialog(null, "No Quiz Found", "Failed", JOptionPane.ERROR_MESSAGE);
                jsonString = "NO";
            } else {
                JOptionPane.showMessageDialog(null, "Startin the Quiz", "Successful", JOptionPane.INFORMATION_MESSAGE);

            }

            clientsocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    public void score(String un, String qid, String s, int c) {

        String username = un;
        String quiz_id = qid;
        String score = s;
        this.code = c;

        String line = username + "," + quiz_id;
        String msg = line + ":" + code + ":" + score;

        try {
            clientsocket = new Socket(address, port);

            PrintWriter out = new PrintWriter(clientsocket.getOutputStream(), true);
            out.println(msg);

            InputStream inputStream = clientsocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            response = reader.readLine();

            JOptionPane.showMessageDialog(null, response, "Successful", JOptionPane.INFORMATION_MESSAGE);

            clientsocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String viewQuiz(String s, int c) {
        String jsonString = null;
        String subject = s;
        int code = c;
        String response = "[]";

        String msg = "test:" + code + ":" + subject;
        try {
            clientsocket = new Socket(address, port);

            PrintWriter out = new PrintWriter(clientsocket.getOutputStream(), true);
            out.println(msg);

            InputStream inputStream = clientsocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            jsonString = reader.readLine();

            if (jsonString.equals(response)) {
                JOptionPane.showMessageDialog(null, "No Quiz Found", "Failed", JOptionPane.ERROR_MESSAGE);
                jsonString = "NO";
            }

            clientsocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonString;

    }
    
    public String viewscore(String s, int c){
        String jsonString = null;
        String subject = s;
        int code = c;
        String response = "[]";

        String msg = "test:" + code + ":" + subject;
        try {
            clientsocket = new Socket(address, port);

            PrintWriter out = new PrintWriter(clientsocket.getOutputStream(), true);
            out.println(msg);

            InputStream inputStream = clientsocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            jsonString = reader.readLine();

            if (jsonString.equals(response)) {
                JOptionPane.showMessageDialog(null, "No Result Found", "Failed", JOptionPane.ERROR_MESSAGE);
                jsonString = "NO";
            }

            clientsocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonString;
    }
}
