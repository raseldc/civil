/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.util;

/**
 *
 * @author rasel
 */
public enum EnumPageType {
    ParentPage(0),
    MenuPage(3),
    Other(4);
//    
    private int value;

    EnumPageType(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
