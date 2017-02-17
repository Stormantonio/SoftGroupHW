package javaHW4.multiChat;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Anton on 14.02.2017.
 */
public class ChatClient implements Runnable {

    private static Socket clientSocket = null;
    private static PrintStream os = null;
    private static BufferedReader reader = null;
    private static boolean closed = false;
    private static BufferedReader br = null;

    public static void main(String[] args) {
        int portNumber = 2222;
        String host = "localhost";

        try {
            clientSocket = new Socket(host, portNumber);
            reader = new BufferedReader(new InputStreamReader(System.in));
            os = new PrintStream(clientSocket.getOutputStream());
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to the host " + host);
        } finally {
            if (clientSocket != null && os != null && br != null) {
                try {
                    new Thread(new ChatClient()).start();
                    while (!closed) {
                        os.println(reader.readLine().trim());
                    }
                    os.close();
                    br.close();
                    clientSocket.close();
                } catch (IOException e) {
                    System.err.println("IOException: " + e);
                }
            }
        }
    }

    public void run() {
        String responseLine;
        try {
            while ((responseLine = br.readLine()) != null) {
                System.out.println(responseLine);
                if (responseLine.contains("*** Bye"))
                    break;
            }
            closed = true;
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
    }
}
