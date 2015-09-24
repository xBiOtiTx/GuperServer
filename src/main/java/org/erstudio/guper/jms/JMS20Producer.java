/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.erstudio.guper.jms;

import java.util.UUID;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.erstudio.guper.model.BusinessObject;

/**
 *
 * @author Евгений
 */
@Path("/test")
@Stateless 
public class JMS20Producer {

    @Resource(lookup = "jms/queue/guper")
    private Queue queue;

    @Inject
    private JMSContext jmsContext;

    @Path("/jms20")
    @GET
    public String produce() {
        BusinessObject payload = new BusinessObject(UUID.randomUUID().toString());
        jmsContext.createProducer().send(queue, payload);
        return "OK";
    }
}
