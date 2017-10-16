package GUI.run;

import Data.PixelsDifference;
import Model.CanvasInteraction;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by luanboheng on 16/10/2017.
 */
public class TimerThread extends Thread{

    CanvasInteraction model;
    int[][] CanvasMatrix;
    MessageQueue SendQueue = new MessageQueue();
    MessageQueue ReceiveQueue = new MessageQueue();


    TimerThread(CanvasInteraction model){
        this.model = model;
        CanvasMatrix = model.getCurrentCanvas();
    }

    @Override
    public void run() {
        super.run();
        Timer timer = new Timer();
        long delay = 1 * 1000;
        long period = 1000;
        timer.schedule(new TimerTasks(), delay, period);
    }

    class TimerTasks extends TimerTask{
        @Override
        public void run() {

            int [][] newCanvas = model.getCurrentCanvas();
            PixelsDifference difference =  model.getCanvasDifference(CanvasMatrix,newCanvas);
            CanvasMatrix = newCanvas;
            if (difference.size() > 0) {
                model.updateNetworkCanvas(difference);
            }
            //SendQueue.add(difference);
            SendQueue.matchCancel(ReceiveQueue);

            if (SendQueue.isEmpty) {
                model.clearPermanentCanvas();
            }
        }
    }
}
