package Server;

import Data.CanvasLog;
import Data.Message;
import Data.PixelsDifference;

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

        System.out.println(message);

        if (message == null)
            return null;

        ArrayList<Message> responses = new ArrayList<>();

        String canvasName;

        String username;

        ServerCanvas canvas;

        PixelsDifference operation;

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
                                    .JOIN_RESPONSE, null));
                        // approved
                        else {
                            responses.add(new Message(response_answers[0], message.id(), Message
                                    .JOIN_RESPONSE, canvas.getCanvasAsPixelDifference()));
                            canvas.addUser(response_answers[0]);
                        }

                    }
                    break;

                case Message.DRAW_OPERATION:

                    canvas = findCanvasByUsername(message.username());

                    operation = (PixelsDifference)message.content();

                    if (canvas != null && operation.size() > 0) {
                        canvas.updateCanvas(operation);
                        for (String user : canvas.getUsers())
                            responses.add(new Message(user, message.id(), message.type(),
                                    operation));
                    }
                    break;

                case Message.UNDO:

                    canvas = findCanvasByManager(message.username());

                    if (canvas != null) {
                        operation = canvas.undoCanvas();

                        for (String user : canvas.getUsers())
                            responses.add(new Message(user, message.id(), message.type(),
                                    operation));
                    }
                    break;

                case Message.CHAT_MESSAGE:

                    canvas = findCanvasByUsername(message.username());

                    if (canvas != null) {
                        for (String user : canvas.getUsers())
                            responses.add(new Message(user, message.id(), message.type(),
                                    message.content()));
                    }
                    break;

                case Message.KICK_USER:

                    canvas = findCanvasByManager(message.username());

                    if (canvas != null) {
                        String userGotKicked = (String)message.content();
                        canvas.removeUser(userGotKicked);
                        responses.add(message);
                        responses.add(new Message(userGotKicked, message.id(), Message
                                .USER_GOT_KICKED, null));
                    }
                    break;

                default:
                    responses.clear();


            }
        } catch (Exception e) {
            System.out.println("Invalid Message Received.");
        }

        responses.add(message);
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
