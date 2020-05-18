/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.util.Enum;

/**
 *
 * @author rasel
 */
public enum Error {

    Admin("ROLE_ADMIN"),
    User("ROLE_USER"),
    Operator("ROLE_OPERATOR");
    private String value;

    Error(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
