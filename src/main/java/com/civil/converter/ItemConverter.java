/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.converter;

import com.civil.detail.ItemDetail;
import com.civil.model.Item;

/**
 *
 * @author Rasel
 */
public class ItemConverter {

    public static Item getEntity(ItemDetail detail) {
        if (detail == null) {
            return null;
        }
        Item entity = new Item();

        entity.setId(detail.getId() == 0 ? null : detail.getId());
        entity.setDescription(detail.getDescription());
        entity.setCode(detail.getCode());
        entity.setPrice(detail.getPrice());
        entity.setUnit(detail.getUnit() == null ? "" : detail.getUnit());
        return entity;
    }

    public static ItemDetail getDetail(Item entity) {
        if (entity == null) {
            return null;
        }

        ItemDetail detail = new ItemDetail();
        detail.setId(entity.getId());
        detail.setDescription(entity.getDescription() == null ? "" : entity.getDescription());
        detail.setPrice(entity.getPrice() == null ? 0 : entity.getPrice());
        detail.setCode(entity.getCode() == null ? "" : entity.getCode());
        detail.setUnit(entity.getUnit() == null ? "" : entity.getUnit());

        detail.setParentItemDescription(entity.getParentItemDescription());
        detail.setShowCode(entity.getShowCode());

        return detail;
    }

}
