/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.erstudio.guper.service;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import org.erstudio.guper.dao.UserDao;
import org.erstudio.guper.exception.LocationException;
import org.erstudio.guper.model.Location;
import org.erstudio.guper.model.Message;
import org.erstudio.guper.model.User;
import org.joda.time.DateTime;

/**
 *
 * @author Евгений
 */
@ApplicationScoped
public class UserService {

    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @Inject
    private UserDao userDao;

    @Inject
    private MessageService messageService;

    public Set<Session> getPeers() {
        return peers;
    }
    
    public User getUser(Session session) {
        User user = userDao.findBySessionId(session.getId());
        if (user == null) {
            user = userDao.save(new User(session.getId(), null, new DateTime()));
        }
        return user;
    }

    private void checkLocation(Location location) throws LocationException {

    }

    public void updateLocation(Location location, Session session) throws IOException, EncodeException, LocationException {
        checkLocation(location);

        User user = getUser(session);
        user.setLocation(location);
        userDao.update(user);

        List<Message> messages = messageService.getMessagesForUser(user);
        for (Message message : messages) {
            session.getBasicRemote().sendObject(message);
        }
    }

    public void connectUser(Session session) {
        User user = getUser(session);
        peers.add(session);
    }

    public void disconnectUser(Session session) {
        User user = userDao.findBySessionId(session.getId());
        if (user != null) {
            userDao.delete(user);
        }
        peers.remove(session);
    }

}
