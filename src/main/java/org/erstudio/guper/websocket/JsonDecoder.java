/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.erstudio.guper.websocket;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author Евгений
 */
public class JsonDecoder implements Decoder.Text<JsonObject> {

    @Override
    public void init(EndpointConfig config) {
        System.out.println("JsonDecoder.init");
    }

    @Override
    public void destroy() {
        System.out.println("JsonDecoder.destroy");
    }

    @Override
    public JsonObject decode(String string) throws DecodeException {
        System.out.println("JsonDecoder.decode");
        JsonObject jsonObject = Json.createReader(new StringReader(string)).readObject();
        return  jsonObject;
    }

    @Override
    public boolean willDecode(String string) {
        boolean result = false;
        try {
            Json.createReader(new StringReader(string)).readObject();
            result = true;
        } catch (Exception ex) {
        }
        System.out.println("JsonDecoder.willDecode " + result);
        return result;
    } 
}
