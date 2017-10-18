package Server;

import Data.Message;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Sender implements Runnable{
    private ObjectOutputStream out;
    private String username;

    public Sender(ObjectOutputStream out, String username) {
        this.out = out;
        this.username = username;
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (Message serverMessage: Network.senderDict.get(username))
                    out.writeObject(serverMessage);
                Network.senderDict.replace(username, new ArrayList<>());
                Thread.sleep(30);
            }

        } catch (IOException e) {
            System.out.println("IOException2: " + e.getMessage());
        } catch (Exception e) {
            if (e.getMessage() == null) {
                Network.senderDict.remove(username);
            }
            else
                System.out.println("IOException2: " + e.getMessage());
            System.out.println("Unexpected exception2: " + e.getMessage());

        }
    }
}
