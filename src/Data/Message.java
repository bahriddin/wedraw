package Data;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by zy on 16/10/17.
 */
public class Message implements Serializable {

    private String usernameValue;
    private int idValue;
    static Random randomGenerator = new Random();

    private int typeValue;
    public static final int DRAW_OPERATION = 1;

    // contentValue should be immutable
    private Serializable contentValue;

    public String username() {
        return usernameValue;
    }

    public int id() {
        return idValue;
    }

    public int type() {
        return typeValue;
    }

    public Serializable content() {
        return contentValue;
    }

    public Message(String user, int typeValue, Serializable contentValue){
        this.usernameValue = user;
        this.typeValue = typeValue;
        this.contentValue = contentValue;
        this.idValue = randomGenerator.nextInt();
    }

    @Override
    public boolean equals(Object obj) {
        Message message= (Message) obj;
        return username().equals(message.username())
                && id()==message.id()
                && type() == message.type();
    }

    public static void main(String[] args) {
        Message a = new Message("sd",Message.DRAW_OPERATION,null);
        System.out.print(a.id());
    }
}
