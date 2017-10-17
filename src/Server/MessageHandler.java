package Server;

import Data.CanvasLog;
import Data.Message;

import java.util.ArrayList;

/**
 * Created by zy on 16/10/17.
 */
public class MessageHandler {

    ArrayList<ServerCanvas> canvases;

    public MessageHandler() {
        canvases = new ArrayList<>();
    }

    public synchronized ArrayList<Message> handleMessage(Message message) {

        if (message == null)
            return null;

        ArrayList<Message> responses = new ArrayList<>();

        String canvasName;

        String username;

        ServerCanvas canvas;

        Boolean answer;

        try {

            switch (message.type()) {

                case Message.CREATE_CANVAS:

                    canvasName = (String) message.content();
                    if (findCanvasByCanvasName(canvasName) == null) {
                        canvases.add(new ServerCanvas(canvasName, message.username()));
                        responses.add(new Message(message.username(),
                                message.id(), message.type(), " Canvas successfully created."));
                    } else {
                        responses.add(new Message(message.username(),
                                message.id(), message.type(), " Cannot create canvas, as a canvas " +
                                "with the same name is being used."));
                    }
                    break;

                case Message.JOIN_REQUEST:

                    canvasName = (String) message.content();

                    canvas = findCanvasByCanvasName(canvasName);

                    boolean isUserExisted = findCanvasByUsername(message.username()) != null;

                    if (canvas == null || isUserExisted) {
                        responses.add(new Message(message.username(), message.id(), Message
                                .JOIN_RESPONSE, false));
                    } else {
                        responses.add(new Message(canvas.getManager(), message.id(), Message
                                .JOIN_REQUEST, message.username()));
                    }

                    break;

                case Message.JOIN_RESPONSE:

                    canvas = findCanvasByManager(message.username());

                    String[] response_answers = (String[]) message.content();

                    if (canvas == null) {
                        // do nothing
                    } else {

                        // unapproved
                        if (response_answers[1] == "F")
                            responses.add(new Message(response_answers[0], message.id(), Message
                                    .JOIN_RESPONSE, false));
                        // approved
                        else {
                            responses.add(new Message(response_answers[0], message.id(), Message
                                    .JOIN_RESPONSE, true));
                            canvas.addUser(response_answers[0]);
                        }

                    }
                    break;


            }
        } catch (Exception e) {
            System.out.println("Invalid Message Received.");
        }

        return responses;

    }

    private ServerCanvas findCanvasByCanvasName(String canvasName) {
        for (ServerCanvas canvas : canvases)
            if (canvas.getCanvasName().equals(canvasName))
                return canvas;

        return null;
    }

    private ServerCanvas findCanvasByManager(String manager) {
        for (ServerCanvas canvas : canvases)
            if (canvas.getManager().equals(manager))
                return canvas;

        return null;
    }

    private ServerCanvas findCanvasByUsername(String username) {
        for (ServerCanvas canvas : canvases)
            if (canvas.getUsers().size() > 0)
                for (String user : canvas.getUsers())
                    if (user.equals(username))
                        return canvas;

        return null;
    }
}
