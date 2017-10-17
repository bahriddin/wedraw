package Server;

import Data.CanvasLog;
import Data.Message;

import java.util.ArrayList;

/**
 * Created by zy on 16/10/17.
 */
public class MessageHandler {



    public MessageHandler() {
        canvases = new ArrayList<CanvasLog>();
    }

    public synchronized ArrayList<Message> handleMessage(Message message) {

        if (message == null)
            return null;

        switch (message.type()) {

            case Message.JOIN_REQUEST:


        }


    }
}
