package GUI.run;

import Data.Message;
import Data.PixelsDifference;
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
    Network net;
    int[][] CanvasMatrix;
    public static ArrayList<Message>SendQueue = new ArrayList<Message>();
    public static ArrayList<Message> ReceiveQueue = new ArrayList<Message>();;


    TimerThread(CanvasInteraction model,String username){
        this.model = model;
        this.CanvasMatrix = model.getCurrentCanvas();
        this.username = username;
    }

    @Override
    public void run() {
        super.run();
        try {
            net = new Network("localhost", 3000);
        }catch (Exception e){
            //
        }

        System.out.println(username);
        admModel = new AdminInteraction(net,username);
        Timer timer = new Timer();
        long period = (long) (1*1000);
        timer.schedule(new TimerTasks(), period, period);
    }

    class TimerTasks extends TimerTask{
        @Override
        public void run() {
            //System.out.println(admModel);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    //System.out.println("timer");
                    // Update UI here.

                    int [][] newCanvas = model.getCurrentCanvas();
                    PixelsDifference difference =  model.getCanvasDifference(CanvasMatrix,newCanvas);
                    CanvasMatrix = newCanvas;

                    //if the whiteboard have been changed
                    if (difference.size() > 0) {
                        Message DrawMessage = new Message("user",Message.DRAW_OPERATION,difference);
                        SendQueue.add(DrawMessage);
                        net.sendMessage(DrawMessage);
                    }

                    if (!SendQueue.isEmpty())
                    System.out.println("============current Sending Queue =============");
                    for (Message m:SendQueue){
                        System.out.println(m);
                    }


                    //get the ReceiveQueue form network model, and execute the operations in the Queue
                    //and if any message in ReceiveQueue are also in SendQueue
                    //execute it and delete it in SendQueue
                    //ReceiveQueue = Network.getMessages();
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


                    if (SendQueue.isEmpty()) {
                        model.clearPermanentCanvas();
                    }
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
