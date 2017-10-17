package Server;

import Data.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Network {

    /**
     * This is for keeping all threads in one place and give them tasks to do from different threads
     */
    static Map<String, ArrayList<Message>> senderDict = new HashMap<>();

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

//                OutputStream socketOut = socket.getOutputStream();
//                ObjectOutputStream out = new ObjectOutputStream(socketOut);

                Receiver receiver = new Receiver(socket);
                Thread receiverThread = new Thread(receiver);

//                Sender sender = new Sender(socket);
//                senderDict.put(sender, new ArrayList<>());
//                Thread senderThread = new Thread(sender);

                receiverThread.start();
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
