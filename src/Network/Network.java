package Network;

import Data.Message;
import GUI.run.TimerThread;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Network {
    private static ArrayList<Message> messages;

    private Socket client;
    private InputStream clintIn;
    private OutputStream clientOut;
    private ObjectOutputStream out;

    private static Thread listenerThread;

    public Network(String host, int port) {
        // Initialize message Arraylist
        messages = new ArrayList<>();

        try {
            // Create TCP connection with server
            client = new Socket(host, port);
            clintIn = client.getInputStream();
            clientOut = client.getOutputStream();
            out = new ObjectOutputStream(clientOut);

            // Always listen to the server
            listenerThread = new Thread(new Listener(clintIn));
            listenerThread.setPriority(Thread.MIN_PRIORITY);
            listenerThread.start();

        } catch (UnknownHostException uhe) {
            System.out.println("Unknown host: " + host);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please, provide port number and dictionary file name.\n" +
                    "Format: java –jar DictionaryServer.jar <port> <dictionary-file>");
        } catch (SecurityException se) {
            System.out.println("Security issue: " + se.getMessage());
        } catch (IOException io) {
            System.out.println("IOException: " + io.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public void sendMessage(Message message) {
        //System.out.println("sent:"+message);
        try {
            out.writeObject(message);
        } catch (IOException io) {
            System.out.println("IOException: " + io.getMessage());
        }
    }
    /*
    public void sendMessage(Message message) {

        TimerThread.ReceiveQueue.add(message);
    }
    */


    public static ArrayList<Message> getMessages() {
        listenerThread.suspend();
        ArrayList<Message> newMessages = new ArrayList<>();
        newMessages.addAll(messages);
        messages.clear();
        listenerThread.resume();
        return newMessages;
    }

    public void closeConnection() {
        try {
            clintIn.close();
            clientOut.close();
            client.close();
        } catch (IOException io) {
            System.out.println("IOException: " + io.getMessage());
        }

    }

    static void addMessage(Message message) {
        messages.add(message);
    }
}
