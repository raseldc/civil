/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rasel
 * @param <E>
 * @param <K>
 */
@SuppressWarnings("unchecked")
@Repository
@Transactional
public abstract class GenericDaoImpl<E, K extends Serializable> implements GenericDao<E, K> {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;//= HibernateUtil.getSessionFactory();
//user for store size 
    private int count = -1;

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int getCount() {
        return count;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Class<? extends E> daoType;

    /**
     * By defining this class as abstract, we prevent Spring from creating
     * instance of this class If not defined as abstract,
     * getClass().getGenericSuperClass() would return Object. There would be
     * exception because Object class does not hava constructor with parameters.
     */
    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        daoType = (Class) pt.getActualTypeArguments()[0];
    }

    protected Session currentSession() {

        return sessionFactory.getCurrentSession();
    }

    @Override

    public void add(E entity) {

        Session s = currentSession();
//        Transaction tx2 = s.beginTransaction();
        s.persist(entity);
//        tx2.commit();

    }

    @Override
    public void saveOrUpdate(E entity) {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(E entity) {
        Session session = currentSession();
        // Transaction tx2 = session.beginTransaction();      

        session.saveOrUpdate(entity);
        System.out.println("1. Employee save called without transaction, id=");
        System.out.println("*****");
        //  tx2.commit();

    }

    @Override
    public void remove(E entity) {
        currentSession().delete(entity);
    }

    @Override
    public E find(K key) {

        return (E) currentSession().get(daoType, key);
    }

    @Override

    public List<E> getAll() {

        List<E> e = currentSession().createCriteria(daoType).list();
        count = e.size();
        return e;

    }

}
