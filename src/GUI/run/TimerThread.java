package GUI.run;

import Data.Message;
import Data.PixelsDifference;
import Model.CanvasInteraction;
import Network.Network;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by luanboheng on 16/10/2017.
 */
public class TimerThread extends Thread{

    CanvasInteraction model;
    Network net = new Network("localhost",3000);
    int[][] CanvasMatrix;
    public static ArrayList<Message>SendQueue = new ArrayList<Message>();
    ArrayList<Message> ReceiveQueue;


    TimerThread(CanvasInteraction model){
        this.model = model;
        CanvasMatrix = model.getCurrentCanvas();
    }

    @Override
    public void run() {
        super.run();
        Timer timer = new Timer();
        long period = (long) (0.3*1000);
        timer.schedule(new TimerTasks(), period, period);
    }

    class TimerTasks extends TimerTask{
        @Override
        public void run() {
//            System.out.println("timer");

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    int [][] newCanvas = model.getCurrentCanvas();
                    PixelsDifference difference =  model.getCanvasDifference(CanvasMatrix,newCanvas);
                    CanvasMatrix = newCanvas;

                    //if the whiteboard have been changed
                    if (difference.size() > 0) {
                        Message DrawMessage = new Message("user",Message.DRAW_OPERATION,difference);
                        SendQueue.add(DrawMessage);
                    }

                    //get the ReceiveQueue form network model, and execute the operations in the Queue
                    //and if any message in ReceiveQueue are also in SendQueue
                    //execute it and delete it in SendQueue
                    ReceiveQueue = net.getMessages();
                    for (Message m:ReceiveQueue){
                        handleMessage(m);
                        if (SendQueue.contains(m)){
                            SendQueue.remove(m);
                        }
                        net.sendMessage(m);
                    }

                    if (SendQueue.isEmpty()) {
                        model.clearPermanentCanvas();
                    }
                }
            });
        }

        //method to execute different types of message
        public void handleMessage(Message message){
            switch (message.type()){
                case Message.DRAW_OPERATION: model.updateNetworkCanvas((PixelsDifference) message.content());break;
                case Message.JOIN_OPERATION:break;
                case Message.KICK_OPERATION:break;
                case Message.CHAT_OPERATION:break;
            }
        }
    }

}
