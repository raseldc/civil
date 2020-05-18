/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.service;

import com.civil.detail.ItemDetail;
import java.util.List;

/**
 *
 * @author Rasel
 */
public interface ItemService extends GenericService<com.civil.model.Item, Integer> {

    public ItemDetail getItemDetailByItemCode(String code);

    public List<ItemDetail> getItemDetailList(int skip, int take);

    public String insertItemBatch(List<ItemDetail> itemDetails);
}
