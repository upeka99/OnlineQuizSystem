package ServerSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    static Socket clientsocket;

    public static void main(String[] args) {

        final int port = 8080; // server port
        String optional = null;

        ServerSocket serversocket; // instance of ServerSocket class

        try {
            serversocket = new ServerSocket(port); // 
            System.out.println(serversocket); // displays address and the port as the content of the ServerSocket instance

            while (true) {

                clientsocket = serversocket.accept();

                InputStream in = clientsocket.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);
                BufferedReader Bfreader = new BufferedReader(reader);
                String msg = Bfreader.readLine();

                String[] message = msg.split(":");
                String line = message[0];
                int code = Integer.parseInt(message[1]);
                optional = message[2];

                if (code == 1) {
                    LoginL ldb1 = new LoginL(clientsocket);
                    Thread lt = new Thread(ldb1);
                    ldb1.input(line);
                    lt.start();
                } else if (code == 11) {
                    CreateQuiz qdb = new CreateQuiz(clientsocket);
                    Thread q = new Thread(qdb);
                    qdb.input(line, optional);
                    q.start();
                } else if (code == 12) {
                    ViewQuiz vq = new ViewQuiz(clientsocket);
                    Thread q = new Thread(vq);
                    vq.input(optional);
                    q.start();
                } else if (code == 13) {
                    ViewResult vr = new ViewResult(clientsocket);
                    Thread q = new Thread(vr);
                    vr.input(optional);
                    q.start();
                } else if (code == 2) {
                    LoginS sdb1 = new LoginS(clientsocket);
                    Thread st = new Thread(sdb1);
                    sdb1.input(line);
                    st.start();
                } else if (code == 22) {
                    AttemptQuiz qdb = new AttemptQuiz(clientsocket);
                    Thread q = new Thread(qdb);
                    qdb.input(optional);
                    q.start();
                } else if (code == 23) {
                    Score s = new Score(clientsocket);
                    Thread q = new Thread(s);
                    s.input(line, optional);
                    q.start();
                }

            }
        } catch (IOException e) {
            System.out.println(" Cannot open a connection ");
        }

    }
}
