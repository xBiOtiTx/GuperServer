/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.erstudio.guper.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.apache.commons.lang3.StringUtils;
import org.erstudio.guper.exception.LocationException;
import org.erstudio.guper.exception.MessageException;
import org.erstudio.guper.exception.WebSocketMessageException;
import org.erstudio.guper.model.Location;
import org.erstudio.guper.model.Message;
import org.erstudio.guper.service.MessageService;
import org.erstudio.guper.service.UserService;

/**
 * https://netbeans.org/kb/docs/javaee/maven-websocketapi_ru.html
 *
 * @author Евгений
 */
@ServerEndpoint(value = "/guperendpoint",
        encoders = {MessageEncoder.class},
        decoders = {JsonDecoder.class})
public class GuperWSEndpoint {
    private final ObjectMapper mapper = new ObjectMapper();

    @Inject
    private UserService userService;

    @Inject
    private MessageService messageService;

    @OnMessage
    public void onWebSocketMessage(JsonObject json, Session session) throws WebSocketMessageException, MessageException, LocationException, IOException, EncodeException {
        System.out.println("onWebSocketMessage " + session.getId());

        if (!json.containsKey("type")) {
            throw new WebSocketMessageException("Type is undefined");
        }
        
        String type = json.getString("type");
        if (StringUtils.isBlank(type)) {
            throw new WebSocketMessageException("Empty message type");
        }
        
        if (!json.containsKey("message")) {
            throw new WebSocketMessageException("Message is undefined");
        }

        String message = json.get("message").toString();
        if (StringUtils.isBlank(message)) {
            throw new WebSocketMessageException("Empty message");
        }

        switch (type) {
            case "SEND_MESSAGE": {
                try {
                    Message m = mapper.readValue(message, Message.class);
                    onSendMessage(m, session);
                } catch (IOException ex) {
                    throw new WebSocketMessageException("Incorrect Message format: " + message);
                }
            }
            break;

            case "CHANGE_LOCATION": {
                try {
                    Location l = mapper.readValue(message, Location.class);
                    onChangeLocation(l, session);
                } catch (IOException ex) {
                    throw new WebSocketMessageException("Incorrect Location format: " + message);
                }
            }
            break;

            default:
                throw new WebSocketMessageException("Incorrect message type: " + type);
        }

    }

    private void onSendMessage(Message message, Session session) throws MessageException, IOException, EncodeException {
        messageService.sendMessage(message, session);
    }

    private void onChangeLocation(Location location, Session session) throws LocationException, IOException, EncodeException  {
        userService.updateLocation(location, session);
    }

    @OnOpen
    public void onOpen(Session peer) throws IOException, EncodeException {
        System.out.println("onOpen " + peer.getId());
        userService.connectUser(peer);
    }

    @OnClose
    public void onClose(Session peer) {
        System.out.println("onClose " + peer.getId());
        userService.disconnectUser(peer);
    }

    @OnError
    public void onError(Throwable error, Session peer) {
        System.out.println("onError " + error.getMessage());
    }
}
