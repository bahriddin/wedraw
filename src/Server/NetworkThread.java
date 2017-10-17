package Server;

import Data.Message;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NetworkThread implements Runnable{

    private Socket socket;
    private static MessageHandler messageHandler;
    private static Map<String, Socket> clientsDict;

    public NetworkThread(Socket socket) {
        this.socket = socket;
        messageHandler = new MessageHandler();
        clientsDict = new HashMap<>();
    }

    @Override
    public void run() {
        try {
            InputStream socketIn = this.socket.getInputStream();
            ObjectInputStream in = new ObjectInputStream(socketIn);
            Message clientMessage;


            while ((clientMessage = (Message) in.readObject()) != null) {

//                System.out.println(clientMessage);
//                System.out.println(clientsDict==null);

                if (!clientsDict.containsKey(clientMessage.username())) {
                    clientsDict.put(clientMessage.username(), socket);
                }

                ArrayList<Message> messages = messageHandler.handleMessage(clientMessage);

                for (Message serverMessage : messages) {
                    Socket socket = clientsDict.get(serverMessage.username());
                    OutputStream socketOut = socket.getOutputStream();
                    ObjectOutputStream out = new ObjectOutputStream(socketOut);
                    out.writeObject(serverMessage);
                    socketOut.close();
                }
            }
            socketIn.close();

        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected exception: " + e.getMessage());
        }

    }
}
