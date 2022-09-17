/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sales.Model;

/**
 *
 * @author PC_2023
 */
public class LineOfInvoice {
    private int itemnum;
    private String itemName;
    private double itemPrice;
    private int itemCount;
    private Invoice invoice;
    

    public LineOfInvoice() {
    }

    public LineOfInvoice(int itemnum, String itemName, double itemPrice, int itemCount, Invoice invoice) {
        this.itemnum = itemnum;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
        this.invoice = invoice;
    }

    // new Fun 
    public double getTotalCount(){
       return  itemCount*itemPrice;
    }
    
    
    public int getItemnum() {
        return itemnum;
    }

    public void setItemnum(int itemnum) {
        this.itemnum = itemnum;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    @Override
    public String toString() {
        return "LineOfInvoice{" + "itemnum=" + itemnum + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemCount=" + itemCount + '}';
    }
    
    
    
}
