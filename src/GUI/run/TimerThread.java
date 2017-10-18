package GUI.run;

import Data.Message;
import Data.PixelsDifference;
import GUI.Layout.Manager;
import Model.AdminInteraction;
import Model.CanvasInteraction;
import Network.Network;
import javafx.application.Platform;

import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by luanboheng on 16/10/2017.
 */
public class TimerThread extends Thread{

    public String username;
    CanvasInteraction model;
    public static AdminInteraction admModel;

    boolean clear;
    Network net;
    int[][] CanvasMatrix;
    int[][] newCanvas;
    PixelsDifference difference = null;

    public static ArrayList<Message>SendQueue = new ArrayList<Message>();
    ArrayList<Message> ReceiveQueue;
    public static Timer timer = new Timer();


    TimerThread(CanvasInteraction model,String username,Network net){
        this.model = model;
        this.CanvasMatrix = model.getCurrentCanvas();
        this.username = username;
        this.net = net;
    }

    @Override
    public void run() {

        System.out.println(username);
        admModel = new AdminInteraction(net,username, model);
        long period = (long) (0.5*1000);
        timer.schedule(new TimerTasks(), period, period);
    }

    class TimerTasks extends TimerTask{
        @Override
        public void run() {
            //System.out.println(admModel);


            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    newCanvas = model.getCurrentCanvas();
                    difference =  model.getCanvasDifference(CanvasMatrix,newCanvas);
                    CanvasMatrix = newCanvas;
                }
            });


            //if the whiteboard have been changed
            if (difference!=null && difference.size() > 0) {
                Message DrawMessage = new Message(username,Message.DRAW_OPERATION,difference);
                SendQueue.add(DrawMessage);
                net.sendMessage(DrawMessage);
                clear = true;
            }

            if (!SendQueue.isEmpty())
            System.out.println("============current Sending Queue =============");
            for (Message m:SendQueue){
                System.out.println(m);
            }


            //get the ReceiveQueue form network model, and execute the operations in the Queue
            //and if any message in ReceiveQueue are also in SendQueue
            //execute it and delete it in SendQueue
            ReceiveQueue = Network.getMessages();

            Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Update UI here.
                if (!ReceiveQueue.isEmpty())
                System.out.println("============current Received Queue ===========");
                for (Message m:ReceiveQueue){
                    System.out.println(m);

                        handleMessage(m);

                    if (SendQueue.contains(m)){
                        SendQueue.remove(m);
                    }
                    System.out.println("-----------------------------------------------");
                }

                if (SendQueue.isEmpty()&&clear) {
                    model.clearPermanentCanvas();
                    CanvasMatrix = model.getCurrentCanvas();
                    clear =false;
                }


            }});






            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                }
            });

        }

        //method to execute different types of message
        public void handleMessage(Message message){

            if (message.type()==Message.DRAW_OPERATION){
                model.updateNetworkCanvas((PixelsDifference) message.content());
            }else {
                admModel.handleMessage(message);
            }
        }
    }

}
