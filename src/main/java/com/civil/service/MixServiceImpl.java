/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.service;

import com.civil.dao.MixedDao;
import com.civil.model.Settings;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rasel
 */
@Service
public class MixServiceImpl implements MixService {

    @Autowired
    public MixedDao mixedDao;

    @Override
    public <E> void add(E entity) {
        mixedDao.add(entity);
    }

    @Override
    public <E, K extends Serializable> E find(E enity, K key) {
        return mixedDao.find(enity, key);
    }

    @Override
    public <E> void remove(E entity) {
        mixedDao.remove(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public <E> List<E> getAll(E enitity) {
        return mixedDao.getAll(enitity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public <E> void update(E entity) {
        mixedDao.update(entity);
    }

    /**
     *
     * @param key
     * @return
     */
    @Override
    public Settings getSettingsByKey(String key) {
        return mixedDao.getSettingsByKey(key);
    }
}
