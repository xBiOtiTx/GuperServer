/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.erstudio.guper.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.erstudio.guper.model.User;

/**
 *
 * @author Евгений
 */
@Stateless
public class UserDao extends LongIdentifiableDao<User> {

    public UserDao() {
        super(User.class);
    }

    public User findBySessionId(String sessionId) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        Predicate predicate = criteriaBuilder.equal(root.get("sessionId"), sessionId);
        
        Order order = criteriaBuilder.desc(root.get("id"));
        
        criteriaQuery.select(root).where(predicate).orderBy(order);
        Query query = this.entityManager.createQuery(criteriaQuery);
        query.setMaxResults(1);
        List<User> result = query.getResultList();
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }
}
