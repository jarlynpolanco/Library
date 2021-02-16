/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gbh.library.dao.impl;

import com.gbh.library.contracts.IGenericDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author jarlynpolanco
 * @param <T>
 */
public final class GenericDAOImpl<T> implements IGenericDAO<T> {
    
    @PersistenceContext
    protected EntityManager em;
    private final Class<T> type;

    public GenericDAOImpl(Class<T> type, EntityManager em) {
        this.type = type;
        this.em = em;
    }

    @Override
    public T read(Integer id) throws SQLException {
        return (T) this.em.find(type, id);
    }

    @Override
    public T create(T obj) throws SQLException {
        this.em.getTransaction().begin();
        this.em.persist(obj);
        this.em.getTransaction().commit();
        return obj;
    }

    @Override
    public T update(T obj) throws SQLException {
        this.em.getTransaction().begin();
        this.em.merge(obj);
        this.em.getTransaction().commit();
        return obj;
    }

    @Override
    public void delete(T obj) throws SQLException {
         this.em.getTransaction().begin();
         this.em.remove(this.em.getReference(type, obj));  
         this.em.getTransaction().commit();
    }

    @Override
    public List<T> readAll() throws SQLException {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        Root<T> root = cq.from(type);
        cq.select(root);
        Query q = this.em.createQuery(cq);
        return q.getResultList();
    }

    @Override
    public List<T> readListWithRestrictions(Map<String, Object> criteria) throws SQLException {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> root = cq.from(type);
        List<Predicate> predicates = new ArrayList<>();
        Iterator<String> it = criteria.keySet().iterator();
        while(it.hasNext()){
            String key = it.next();
            predicates.add(cb.equal(root.get(key), criteria.get(key)));
        }
        cq.select(root)
            .where(predicates.toArray(new Predicate[]{}));
         TypedQuery<T> typedQuery = em.createQuery(cq);
         
        return typedQuery.getResultList();
    }

    @Override
    public T readSingleWithRestrictions(Map<String, Object> criteria) throws SQLException {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> root = cq.from(type);
        List<Predicate> predicates = new ArrayList<>();
        Iterator<String> it = criteria.keySet().iterator();
        while(it.hasNext()){
            String key = it.next();
            predicates.add(cb.equal(root.get(key), criteria.get(key)));
        }
        cq.select(root)
            .where(predicates.toArray(new Predicate[]{}));
         TypedQuery<T> typedQuery = em.createQuery(cq);
         
        return typedQuery.getSingleResult();
    }
}
