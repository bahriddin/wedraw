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
    //handle by canvas interaction
    public static final int DRAW_OPERATION = 1;
    public static final int UNDO = 2;
    public static final int CREATE_CANVAS = 3;
    public static final int SAVE_CANVAS = 4;
    public static final int LOAD_CANVAS = 5;

    //handle by chatbox interaction
    public static final int CHAT_MESSAGE = 6;
    public static final int JOIN_REQUEST = 7;
    public static final int JOIN_RESPONSE = 8;
    public static final int KICK_USER = 9;
    public static final int USER_GOT_KICKED = 10;


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

    public Message(String user, int idValue, int typeValue, Serializable contentValue){
        this.usernameValue = user;
        this.typeValue = typeValue;
        this.idValue = idValue;
        this.contentValue = contentValue;
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

    @Override
    public String toString() {
        return  "<Message>User:"+username()+"\tID:"+id()+"\tType:"+type();
    }

    public static void main(String[] args) {
        Message a = new Message("sd",Message.DRAW_OPERATION,null);
        System.out.print(a);
    }
}
