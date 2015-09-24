/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.erstudio.guper.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import org.apache.commons.lang3.StringUtils;
import org.erstudio.guper.dao.MessageDao;
import org.erstudio.guper.exception.MessageException;
import org.erstudio.guper.exception.WebSocketMessageException;
import org.erstudio.guper.model.Location;
import org.erstudio.guper.model.Message;
import org.erstudio.guper.model.User;
import org.joda.time.DateTime;

/**
 *
 * @author Евгений
 */
@ApplicationScoped
public class MessageService {

    @Inject
    private UserService userService;

    @Inject
    private MessageDao messageDao;

    private void checkMessage(Message message) throws MessageException {
        Location location = message.getLocation();
        if(location == null || location.getLat() == null || location.getLon() == null) {  
            throw new MessageException("Location is not defined");
        }
        
        if(StringUtils.isBlank(message.getText())) {
            throw new MessageException("Text is empty");
        }   
    }

    public void sendMessage(Message message, Session session) throws MessageException, IOException, EncodeException {
        checkMessage(message);
        
        message.setDateTime(new DateTime());
        messageDao.save(message);
        
        Set<Session> peers = userService.getPeers();
        for (Session peer : peers) {
            peer.getBasicRemote().sendObject(message);
        }
    }
    
    public List<Message> getMessagesForUser(User user) {
        List<Message> messages = messageDao.findAll();
        return messages;
    }
}
