/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.erstudio.guper.exception;

/**
 *
 * @author Евгений
 */
public class WebSocketMessageException extends Exception {

    public WebSocketMessageException() {
    }

    public WebSocketMessageException(String message) {
        super(message);
    }
}
