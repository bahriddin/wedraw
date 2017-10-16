package Network;

import Data.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/*
  This class would listen to the server
  When new message sent, it will add it to the Network's messages property
 */
public class Listener implements Runnable {
    private InputStream clientIn;

    Listener(InputStream dataIn) {
        this.clientIn = dataIn;
    }

    @Override
    public void run() {
        try {
            Message message;
            ObjectInputStream in = new ObjectInputStream(clientIn);
            while ((message = (Message) in.readObject()) != null) {
                Network.addMessage(message);
            }
        } catch (IOException io) {
            System.out.println("IOException: " + io.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
    }
}
