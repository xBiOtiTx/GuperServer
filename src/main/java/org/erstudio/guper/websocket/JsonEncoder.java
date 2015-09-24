/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.erstudio.guper.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author Евгений
 * @param <T>
 */
public abstract class JsonEncoder<T> implements Encoder.Text<T> {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String encode(T object) throws EncodeException {
        System.out.println("JsonEncoder encode");
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            throw new EncodeException(object, null, ex);
        }
    }

    @Override
    public void init(EndpointConfig config) {
        System.out.println("JsonEncoder init");
    }

    @Override
    public void destroy() {
        System.out.println("JsonEncoder destroy");
    }
}
