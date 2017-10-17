package Server;

import Data.Message;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Sender implements Runnable{
    private Socket socket;
    private String username;

    public Sender(Socket socket, String username) {
        this.socket = socket;
        this.username = username;
    }

    @Override
    public void run() {
        try {
            OutputStream socketOut = socket.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(socketOut);

            while (true) {
                for (Message serverMessage: Network.senderDict.get(username))
                    out.writeObject(serverMessage);
                Network.senderDict.replace(username, new ArrayList<>());
                Thread.sleep(30);
            }

        } catch (IOException e) {
            System.out.println("IOException2: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected exception: " + e.getMessage());
        }
    }
}
