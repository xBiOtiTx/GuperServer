/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.erstudio.guper.dao;

import java.io.Serializable;
import org.erstudio.guper.model.LongIdentifiable;

/**
 *
 * @author Евгений
 * @param <E>
 */
public abstract class LongIdentifiableDao<E extends Serializable> extends AbstractDao<E, LongIdentifiable>{    
    public LongIdentifiableDao(Class<E> entityClass) {
        super(entityClass);
    }
}
