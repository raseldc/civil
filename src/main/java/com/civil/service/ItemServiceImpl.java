/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.service;

import com.civil.detail.ItemDetail;
import com.civil.dao.GenericDao;
import com.civil.dao.ItemDao;
import com.civil.model.Item;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rasel
 */
@Service
public class ItemServiceImpl extends GenericServiceImpl<Item, Integer>
        implements ItemService {

    private ItemDao itemDao;

    @Autowired
    public ItemServiceImpl(
            @Qualifier("itemDaoImpl") GenericDao<Item, Integer> genericDao) {

        super(genericDao);
        this.itemDao = (ItemDao) genericDao;

    }

    @Override
    public ItemDetail getItemDetailByItemCode(String code) {
        return itemDao.getItemDetailByItemCode(code);
    }

    @Override
    public List<ItemDetail> getItemDetailList(int skip, int take) {
        return itemDao.getItemDetailList(skip, take);
    }
     @Override
     public String insertItemBatch( List<ItemDetail> itemDetails)
     {
         return  itemDao.insertItemBatch(itemDetails);
     }
}
