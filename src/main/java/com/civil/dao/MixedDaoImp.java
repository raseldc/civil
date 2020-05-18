/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.dao;


import com.civil.model.Settings;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rasel
 */
@Repository
@Transactional
public class MixedDaoImp implements MixedDao
{

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;//= HibernateUtil.getSessionFactory();

    public <E> void add(E entity)
    {
        Session s = sessionFactory.getCurrentSession();
//        Transaction tx2 = s.beginTransaction();
        s.persist(entity);
//        tx2.commit();

    }

    @Override
    public <E, K extends Serializable> E find(E enitity, K key)
    {

//        Class<? extends E> daoType;
//        Type t = getClass().getGenericSuperclass();
//        ParameterizedType pt = (ParameterizedType) t;
//        daoType = (Class) pt.getActualTypeArguments()[0];
        return (E) sessionFactory.getCurrentSession().get(enitity.getClass(), key);
    }

    @Override
    public <E> void remove(E entity)
    {
        sessionFactory.getCurrentSession().delete(entity);
    }

    public <E> List<E> getAll(E enitity)
    {

        List<E> e = sessionFactory.getCurrentSession().createCriteria(enitity.getClass()).list();
        return e;

    }

   

    @Override
    public <E> void update(E entity)
    {

        sessionFactory.getCurrentSession().saveOrUpdate(entity);

    }

  

    /**
     *
     * @param key
     * @return
     */
    @Cacheable(value = "userList", key = "#key")
    public Settings getSettingsByKey(String key)
    {
        return (Settings) sessionFactory.getCurrentSession().createQuery("SELECT s FROM Settings AS s WHERE s.key = :key").setParameter("key", key).uniqueResult();
    }

}
