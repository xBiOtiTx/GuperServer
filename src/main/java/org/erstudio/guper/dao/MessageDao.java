/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.erstudio.guper.dao;

import javax.ejb.Stateless;
import org.erstudio.guper.model.Message;

/**
 *
 * @author Евгений
 */
@Stateless
public class MessageDao extends LongIdentifiableDao<Message>{
    public MessageDao() {
        super(Message.class);
    }
}
