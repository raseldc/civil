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
 * @author Rasel
 */
public enum EnumFoodTime {

    Breakfast(1),
    Lunch(2),
    Dinner(3),
    Other(4);
//    
    private Integer value;

    EnumFoodTime(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static List<EnumMappingClass> fromValuesToMap() {
        List<EnumMappingClass> enumMappingClassList = new ArrayList<EnumMappingClass>();
        for (EnumFoodTime enumClass : EnumFoodTime.values()) {
            enumMappingClassList.add(new EnumMappingClass(enumClass.name(), enumClass.getValue()));
        }

        return enumMappingClassList;
    }

    public static String nameFromValues(int value) {
        for (EnumFoodTime enumClass : EnumFoodTime.values()) {
            if (enumClass.getValue() == value) {
                return enumClass.name();
            }
        }

        return "";
    }
}
