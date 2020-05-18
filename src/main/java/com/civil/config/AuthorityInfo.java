/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.config;

import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author rasel
 */
public class AuthorityInfo implements GrantedAuthority {

    private static final long serialVersionUID = -175781100474818800L;
    private String method;

    private String url;
    private String displayName;
    private String parentPageName;
    private int type;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getParentPageName() {
        return parentPageName;
    }

    public void setParentPageName(String parentPageName) {
        this.parentPageName = parentPageName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDisplay() {
        return displayName;
    }

    public void setDisplay(String Display) {
        this.displayName = Display;
    }

    public int getStatus() {
        return type;
    }

    public void setStatus(int status) {
        this.type = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public AuthorityInfo(String url, String method) {
        this.url = url;
        this.method = method;
    }

    public AuthorityInfo(String url) {
        this.url = url;
        this.method = "";
    }

    @Override
    public String getAuthority() {
        return this.url + ";" + this.method;
    }

    public void setAuthority(String url, String method) {
        this.url = url;
        this.method = method;
    }

}
