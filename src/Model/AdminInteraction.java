package Model;

import Data.Message;
import Data.PixelsDifference;
import GUI.Layout.WhiteBoard;
import GUI.run.LoadingPageController;
import GUI.run.TimerThread;
import GUI.run.run;
import Network.Network;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Optional;

//import static GUI.Layout.Client.chatBox;


/**
 * Created by luanboheng on 17/10/2017.
 */
public class AdminInteraction {
    String userName;
    Network net;
    CanvasInteraction model;
    TextArea chatbox;

    public void setChatbox(TextArea chatbox){
        this.chatbox = chatbox;
    }


    @Override
    public String toString() {
        return "<Admin>"+userName+",\t"+net;
    }

    //Chatbox chatbox;
    //where Chatbox is a type that have methods that include add new lines on the Chat display field
    //and get text form the textfield
    //This interaction model will be called by timer and the actions that "sent"button "kick"button
    //and some method that allow join request


    public AdminInteraction(Network net, String userName, CanvasInteraction model){
        this.net =net;
        this.userName = userName;
        this.model = model;

    }


    public void handleMessage(Message m){
        switch (m.type()){
            case Message.CREATE_CANVAS:handle_CREATE_CANVAS(m);break;
            case Message.SAVE_CANVAS:handle_SAVE_CANVAS(m);break;
            case Message.LOAD_CANVAS:handle_LOAD_CANVAS(m);break;
            case Message.CHAT_MESSAGE:handle_CHAT_MESSAGE(m);break;
            case Message.JOIN_REQUEST:handle_JOIN_REQUEST(m);break;
            case Message.JOIN_RESPONSE:handle_JOIN_RESPONSE(m);break;
            case Message.KICK_USER:handle_KICK_USER(m);break;
            case Message.USER_GOT_KICKED:handle_USER_GOT_KICKED(m);break;
            case Message.EXIT:handle_EXIT(m);break;
            case Message.SHUTDOWN:handle_SHUTDOWN(m);break;
        }
    }


    public void handle_EXIT (Message m){
        String quitUser = (String) m.content();
        //消失掉对应user的button
        Send_CHAT_MESSAGE("user \""+quitUser+"\" has quit");
        for(Button btn:run.c.manager_whiteboard.user_button_list) {
            if(btn.getText().equals(quitUser))
            {
                run.c.manager_whiteboard.Button_list.getChildren().remove(btn);

        }

    }}

    public void handle_SHUTDOWN (Message m){
        //Shut down

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Info");
//        alert.setHeaderText("Look, an Error Dialog");
        if (m.username().length() > 0)
            alert.setContentText("the manager has exited, system shut down");
        else
            alert.setContentText(" The server is closed.");

        ButtonType buttonTypeOne = new ButtonType("OK");

        alert.getButtonTypes().setAll(buttonTypeOne);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            Platform.exit();
            System.exit(0);
        }

        else  {
            // ... uPlatform.exit();
            Platform.exit();
            System.exit(0);


        }




//        run.c.client_whiteboard.manager_shut_down("the manager has exited");


        //退出系统
    }

    public void handle_LOAD_CANVAS(Message m){
        if (m.content()==null){
            run.c.save_success("Load failed");
            //炸了
        }else{
            PixelsDifference pixelsDifference = (PixelsDifference)m.content();
            model.clearAllCanvases();
            model.updateNetworkCanvas(pixelsDifference);
        }
    }

    public void handle_CREATE_CANVAS(Message m){
        String stringMessage = LocalDateTime.now()+":\n"+m.content();
        //弹窗，显示 stringMessages
        System.out.println(stringMessage);
        LoadingPageController controllerL = run.c;
        if (stringMessage.contains("success")){
            run.c.show_whiteboard(run.c.manager_whiteboard);

        }else{
            run.c.dialog_fail();
        }

    }


    public void handle_SAVE_CANVAS(Message m){
        String stringMessage = LocalDateTime.now()+":\n"+m.content();
        //弹窗，显示 stringMessage
        run.c.save_success(stringMessage);

    }

    public void handle_CHAT_MESSAGE(Message m){
        String stringMessage = (String) m.content();

        chatbox.appendText(stringMessage+"\n");
    }

    public void handle_JOIN_REQUEST(Message m){
        String otheruser =(String)  m.content();
        String response= run.c.manager_whiteboard.join_request_dialog(otheruser);

        Send_JOIN_RESPONSE(otheruser, response);
        if (response.equals("T")) {
            Send_CHAT_MESSAGE("user \"" + m.content() + "\" join the canvas");
        }

    }

    public void handle_JOIN_RESPONSE(Message m){
        //System.out.println("test respond"+ m.content());
        if (m.content()==null){

           run.c.client_join_rejected();


        }
        else{


            run.c.show_whiteboard(run.c.client_whiteboard);
            model.updateNetworkCanvas((PixelsDifference)m.content());

        }

    }

    public void handle_KICK_USER(Message m){
        Send_CHAT_MESSAGE("user \""+m.content()+"\" has been kicked out");
    }

    public void handle_USER_GOT_KICKED(Message m){

        run.c.client_whiteboard.kicked();

    }

    public void Send_UNDO(){
        Message message =  new Message(userName,Message.UNDO,null);
        net.sendMessage(message);
    }


    public void Send_CREATE_CANVAS(String canvasName) {
        Message message =  new Message(userName,Message.CREATE_CANVAS,canvasName);
        try {

            net.sendMessage(message);
        }catch (Exception e){

            run.timerThread.stop();
            TimerThread.timer.cancel();
            run.c.logs.setText("  ");

            run.c.dialog_fail();


        }
    }

    public void Send_NEW_CANVAS (){
        Message message =  new Message(userName,Message.LOAD_CANVAS,null);
        net.sendMessage(message);
    }

    public void Send_SAVE_CANVAS(String canvasName){
        //canvasName 是本地保存的 canvasName
        Message message =  new Message(userName,Message.SAVE_CANVAS,canvasName);
        net.sendMessage(message);
    }

    public void Send_LOAD_CANVAS(String canvasName){
        //canvasName 是本地保存的 canvasName
        Message message =  new Message(userName,Message.LOAD_CANVAS,canvasName);
        net.sendMessage(message);
    }

    public void Send_CHAT_MESSAGE  (String text){
        text = "["+userName+"]: "+text+"\n"+LocalDateTime.now() +"\n";
        Message message =  new Message(userName,Message.CHAT_MESSAGE,text);
        net.sendMessage(message);
    }

    public void Send_KICK_USER (String UserToBeKicked){
        Message message =  new Message(userName,Message.KICK_USER,UserToBeKicked);
        net.sendMessage(message);
    }

    public void Send_JOIN_REQUEST (String canvasName){
        Message message =  new Message(userName,Message.JOIN_REQUEST,canvasName);
        net.sendMessage(message);
    }

    public void Send_JOIN_RESPONSE (String name, String response){
        String[] name_response = new String[2];
        name_response[0] = name;
        name_response[1] = response;
        Message message =  new Message(userName,Message.JOIN_RESPONSE,name_response);

        net.sendMessage(message);
        if(response.contains("T")){
            run.c.manager_whiteboard.generate_user( name);
        }
    }
}
