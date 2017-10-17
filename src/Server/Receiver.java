package Server;

import Data.Message;
import sun.nio.ch.Net;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Receiver implements Runnable {
    private Socket socket;
    private static MessageHandler messageHandler = new MessageHandler();

    public Receiver(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream socketIn = this.socket.getInputStream();
            ObjectInputStream in = new ObjectInputStream(socketIn);
            System.out.println(socket);

            Message clientMessage = (Message) in.readObject();
            String username = clientMessage.username();

            if (Network.senderDict.containsKey(username)) {
                throw new Exception("There is a user with this username.");
            } else {
                Sender sender = new Sender(socket, username);
                Network.senderDict.put(username, new ArrayList<>());
                Thread senderThread = new Thread(sender);
                senderThread.start();
            }

            do {
                for (Message serverMessage: messageHandler.handleMessage(clientMessage))
                    Network.senderDict.get(serverMessage.username()).add(serverMessage);
            } while ((clientMessage = (Message) in.readObject()) != null);

        } catch (IOException e) {
            if (e.getMessage() == null)
                System.out.println("User left the board: ");
            else
                System.out.println("IOException1: " + e.getMessage() + " " + socket);
        } catch (Exception e) {
            System.out.println("Unexpected exception: " + e.getMessage());
        }

    }
}
