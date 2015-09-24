/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.erstudio.guper.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Евгений
 * @param <T>
 * @param <PK>
 */
public abstract class AbstractDao<T extends Serializable, PK extends Serializable> {
    private final Class<T> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    public AbstractDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T save(T t) {
        this.entityManager.persist(t);
        return t;
    }

    public T findById(PK id) {
        return this.entityManager.find(entityClass, id);
    }

    public List<T> findAll() {
        Query query = this.entityManager.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e");
        return query.getResultList();
    }

//    public List<T> find(Order order, Predicate... restrictions) {
//        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
//
//        CriteriaQuery<T> criteriaQuery = cb.createQuery(entityClass);
//        Root<T> c = criteriaQuery.from(entityClass);
//        
//        ParameterExpression<Integer> p = cb.parameter(Integer.class);
//        
//        
//        //q.select(c).where(cb.gt(c.get("population"), p));
//        //cb.gt(c.get("population"), p);
//        
//        criteriaQuery.select(c).where(cb.equal(c.get("sessionId"), p));
//
//        //criteriaQuery.select(c).where(restrictions).orderBy(order);
//        //Query query = this.entityManager.createQuery(criteriaQuery);
//        
//        return null;
//    }
    
//    public List<T> find(Predicate... restrictions) {
//        return null;
//    }
//    
//    public T findUnique(Predicate... restrictions) {
//         CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
//         CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
//         Root<T> root = criteriaQuery.from(entityClass);
//         criteriaQuery.select(root).where(restrictions);
//         Query query = this.entityManager.createQuery(criteriaQuery);
//         query.setMaxResults(1);
//         List<T> result = query.getResultList();
//         if(result.isEmpty()) {
//             return null;
//         }
//         return result.get(0);
//    }
    


    public T update(T t) {
        return this.entityManager.merge(t);
    }

    public void delete(T t) {
        t = this.entityManager.merge(t);
        this.entityManager.remove(t);
    }
}
