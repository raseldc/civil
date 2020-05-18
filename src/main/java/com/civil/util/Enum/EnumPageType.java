/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.util.Enum;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rasel
 */
public enum EnumPageType {

    ParentPage(0),
    MenuPage(3),
    Other(4);
//    
    private Integer value;

    EnumPageType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static List<EnumMappingClass> fromValuesToMap() {
        List<EnumMappingClass> enumMappingClassList = new ArrayList<EnumMappingClass>();
        for (EnumPageType enumClass : EnumPageType.values()) {
            enumMappingClassList.add(new EnumMappingClass(enumClass.name(), enumClass.getValue()));
        }

        return enumMappingClassList;
    }

    public static String nameFromValues(int value) {
        for (EnumPageType enumClass : EnumPageType.values()) {
            if (enumClass.getValue() == value) {
                return enumClass.name();
            }
        }

        return "";
    }
}
