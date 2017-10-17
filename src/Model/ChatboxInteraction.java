package Model;

import Data.Message;

import java.time.LocalDateTime;

/**
 * Created by luanboheng on 17/10/2017.
 */
public class ChatboxInteraction {
    String userName;
    //Chatbox chatbox;
    //where Chatbox is a type that have methods that include add new lines on the Chat display field
    //and get text form the textfield
    //This interaction model will be called by timer and the actions that "sent"button "kick"button
    //and some method that allow join request


    //handle by chatbox interaction
//    public static final int CHAT_MESSAGE = 6;
//    public static final int JOIN_REQUEST = 7;
//    public static final int JOIN_RESPONSE = 8;
//    public static final int KICK_USER = 9;
//    public static final int USER_GOT_KICKED = 10;

    public void handleMessage(Message m){
        switch (m.type()){
            case Message.CHAT_MESSAGE:handleChatMessage(m);break;
            case Message.KICK_USER:break;
            //manager 收到server的回复
            case Message.USER_GOT_KICKED:break;
            //断掉自己
            case Message.JOIN_REQUEST:break;
            case Message.JOIN_RESPONSE:break;
        }
    }

    public void handleChatMessage (Message m){
        String stringMessage = m.username()+":\t"+m.content();
        System.out.print(stringMessage);
    }
    public Message generateChatMessage (String text){
        text = ""+LocalDateTime.now() +"\n"+text;
        return new Message(userName,Message.CHAT_MESSAGE,text);
    }

    public Message generateKickMessage (String kickedUser){
        return new Message(userName,Message.KICK_USER,kickedUser);
    }

    public Message generateJoinMessage (String CanvasId){
        return new Message(userName,Message.JOIN_REQUEST,CanvasId);
    }

    public void handleKickMessage (Message m){
        //this user disconnect itself form the server
    }
}
