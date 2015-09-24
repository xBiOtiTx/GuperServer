/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.erstudio.guper.model;

import java.io.Serializable;

/**
 *
 * @author Евгений
 */
public class BusinessObject implements Serializable {
    private final String name;

    public BusinessObject(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "BusinessObject [name = " + name + "]";
    }
}
