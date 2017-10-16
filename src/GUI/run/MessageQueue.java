package GUI.run;

import Data.Message;

import java.util.ArrayList;

/**
 * Created by luanboheng on 16/10/2017.
 */
public class MessageQueue extends ArrayList<Message> {
    public boolean isEmpty;

    public void matchCancel (ArrayList<Message> otherQueue){
        for (int i = 0; i<size();i++){
            Message message = get(i);
            if (otherQueue.contains(message)){
                this.remove(message);
                otherQueue.remove(message);
            }
        }
    }
}
