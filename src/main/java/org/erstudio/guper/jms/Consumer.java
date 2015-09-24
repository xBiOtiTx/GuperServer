/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.erstudio.guper.jms;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import org.erstudio.guper.model.BusinessObject;

/**
 *
 * @author Евгений
 */
@MessageDriven(mappedName = "jms/queue/guper")
public class Consumer implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            BusinessObject payload = message.getBody(BusinessObject.class);
            System.out.println("Message received: " + payload);
        } catch (JMSException e) {
            System.err.println("Error fetching msg payload: " + e.getMessage());
        }
    }
}
