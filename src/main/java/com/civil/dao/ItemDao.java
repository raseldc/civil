/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.dao;

import com.civil.detail.ItemDetail;
import java.util.List;

/**
 *
 * @author Rasel
 */
public interface ItemDao extends GenericDao<com.civil.model.Item, Integer> {

    public ItemDetail getItemDetailByItemCode(String code);

    List<ItemDetail> getItemDetailList(int skip, int take);

    public String insertItemBatch(final List<ItemDetail> itemDetails);
}
