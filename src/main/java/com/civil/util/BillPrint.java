/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.util;

/**
 *
 * @author Rasel
 */
public class BillPrint extends JsonResult {

    private String val1;
    private String val2;
    private String val3;
    private String val4;

    public BillPrint() {
    }

    public BillPrint(String v1,String v2,String v3,String v4) {
        this.val1 = v1;
        this.val2 = v2;
        this.val3 = v3;
        this.val4 = v4;
    }

    public String getVal1() {
        return val1;
    }

    public void setVal1(String val1) {
        this.val1 = val1;
    }

    public String getVal2() {
        return val2;
    }

    public void setVal2(String val2) {
        this.val2 = val2;
    }

    public String getVal3() {
        return val3;
    }

    public void setVal3(String val3) {
        this.val3 = val3;
    }

    public String getVal4() {
        return val4;
    }

    public void setVal4(String val4) {
        this.val4 = val4;
    }

}
