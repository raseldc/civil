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
public enum EnumRole {
    Admin("ROLE_ADMIN"),
    User("ROLE_USER"),
    Operator("ROLE_OPERATOR");
//    
    private String value;

    EnumRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
