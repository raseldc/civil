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
public class InvoiceTotalBill extends JsonResult{

    private double foodBill;
    private double rommBill;
    private double otherBill;
    private double totalBill;
    private double paidBill;
    private double dueBill;

    
    

    public double getFoodBill() {
        return foodBill;
    }

    public void setFoodBill(double foodBill) {
        this.foodBill = foodBill;
    }

    public double getRommBill() {
        return rommBill;
    }

    public void setRommBill(double rommBill) {
        this.rommBill = rommBill;
    }

    public double getOtherBill() {
        return otherBill;
    }

    public void setOtherBill(double otherBill) {
        this.otherBill = otherBill;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }

    public double getPaidBill() {
        return paidBill;
    }

    public void setPaidBill(double paidBill) {
        this.paidBill = paidBill;
    }

    public double getDueBill() {
        return dueBill;
    }

    public void setDueBill(double dueBill) {
        this.dueBill = dueBill;
    }

}
