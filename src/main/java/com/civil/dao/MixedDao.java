/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.dao;

import com.civil.model.Settings;
import java.io.Serializable;
import java.util.List;


/**
 *
 * @author rasel
 */
public interface MixedDao
{

    public <E> void add(E entity);

    public <E> void update(E entity);

    public <E, K extends Serializable> E find(E enitity, K key);

    public <E> void remove(E entity);

    public <E> List<E> getAll(E enitity);



    /**
     *
     * @param key
     * @return
     */
    public Settings getSettingsByKey(String key);
}
