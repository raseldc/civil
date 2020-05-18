/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.util;

import java.util.Date;

/**
 *
 * @author Rasel
 */
public class SearchInfoForReport {

    private String fromDate_st;
    private String toDate_st;
    private int status;
    private Date fromDate;
    private Date toDate;

    public String getFromDate_st() {
        return fromDate_st;
    }

    public void setFromDate_st(String fromDate_st) {
        this.fromDate_st = fromDate_st;
    }

    public String getToDate_st() {
        return toDate_st;
    }

    public void setToDate_st(String toDate_st) {
        this.toDate_st = toDate_st;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
    
    

}
