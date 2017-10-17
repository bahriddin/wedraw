package Server;

import Data.Message;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NetworkThread implements Runnable{

    private Socket socket;
    private static MessageHandler messageHandler = new MessageHandler();
    private static Map<String, ObjectOutputStream> clientsDict = new HashMap<>();

    public NetworkThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream socketIn = this.socket.getInputStream();
            ObjectInputStream in = new ObjectInputStream(socketIn);

            OutputStream socketOut = socket.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(socketOut);

            Object obj;
            Message clientMessage;


            while ((obj = in.readObject()) != null) {
//                System.out.println("obj = " + obj);
                clientMessage = (Message) obj;
//                System.out.println(clientMessage);
//                System.out.println(clientsDict==null);

                if (!clientsDict.containsKey(clientMessage.username())) {

                    clientsDict.put(clientMessage.username(), out);
                } else if (clientsDict.get(clientMessage.username()) != out) {
                    throw new Exception("There is another client with the same name");
                }

                ArrayList<Message> messages = messageHandler.handleMessage(clientMessage);

                for (Message serverMessage : messages) {
                    ObjectOutputStream ooo = clientsDict.get(serverMessage.username());
                    ooo.writeObject(serverMessage);
                }
            }

        } catch (IOException e) {
            System.out.println("IOException1: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected exception: " + e.getMessage());
        }

    }
}
