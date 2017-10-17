package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Network {
    public static void main(String[] args) {
        // write your code here
        int port;

        try {
            port = Integer.valueOf(args[0]);

            // register socket on provided port
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is listening port " + port + "...");

            while (true) {
                Socket socket = serverSocket.accept();
                NetworkThread threadObj = new NetworkThread(socket);
                Thread networkThread = new Thread(threadObj);
                networkThread.start();
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please, provide port number and dictionary file name.\n" +
                    "Format: java –jar DictionaryServer.jar <port> <dictionary-file>");
        } catch (SecurityException se) {
            System.out.println("Security issue: " + se.getMessage());
        } catch (IOException io) {
            System.out.println("IOException2: " + io.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
