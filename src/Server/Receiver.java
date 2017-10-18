package Server;

import Data.Message;
import sun.nio.ch.Net;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Receiver implements Runnable {
    private Socket socket;
    private static MessageHandler messageHandler = new MessageHandler();
    private String myName;

    public Receiver(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream socketIn = this.socket.getInputStream();
            ObjectInputStream in = new ObjectInputStream(socketIn);
//            System.out.println(socket);

            OutputStream socketOut = socket.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(socketOut);

            Message clientMessage = (Message) in.readObject();
            String username = clientMessage.username();
            if (myName == null)
                myName = username;

            if (Network.senderDict.containsKey(username)) {
                out.writeObject(new Message(username, Message.JOIN_RESPONSE, null));
                throw new Exception("There is a user with this username.");
            } else {
                Sender sender = new Sender(out, username);
                Network.senderDict.put(username, new ArrayList<>());
                Thread senderThread = new Thread(sender);
                senderThread.start();
            }

            do {
                for (Message serverMessage: messageHandler.handleMessage(clientMessage))
                    Network.senderDict.get(serverMessage.username()).add(serverMessage);
            } while ((clientMessage = (Message) in.readObject()) != null);

        } catch (IOException e) {
            if (e.getMessage() == null) {
                //System.out.println("User left the board: ");
                Message userLeftMessage = new Message(myName, Message.EXIT, null);
                Network.senderDict.remove(myName);
                System.out.println("Removed from senderDict");
                for (Message serverMessage: messageHandler.handleMessage(userLeftMessage))
                    try{
                        Network.senderDict.get(serverMessage.username()).add(serverMessage);
                    } catch (Exception eeee) {

                    }
            }
            else
                System.out.println("IOException1: " + e.getMessage() + " " + socket);
        } catch (Exception e) {
            System.out.println("Unexpected exception1: " + e.getMessage());
        }

    }
}
