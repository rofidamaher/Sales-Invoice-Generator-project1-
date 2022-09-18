/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sales.Model;

import java.util.ArrayList;

/**
 *
 * @author PC_2023
 */
public class Invoice {
    
    private int num_Customer ;
    private String date_Customer;
    private String name_Customer;
    private ArrayList<LineOfInvoice> lines;

     public Invoice(){
     }

    public Invoice(int num_Customer, String date_Customer, String name_Customer) {
        this.num_Customer = num_Customer;
        this.date_Customer = date_Customer;
        this.name_Customer = name_Customer;
    }
    
    public double getTotalForAllItems(){
        double t= 0;
        for ( LineOfInvoice l : getLines()){
            t+=l.getTotalCount();
        }
       return  t;
    }

    public int getNum_Customer() {
        return num_Customer;
    }

    public void setNum_Customer(int num_Customer) {
        this.num_Customer = num_Customer;
    }

    public String getDate_Customer() {
        return date_Customer;
    }

    public void setDate_Customer(String date_Customer) {
        this.date_Customer = date_Customer;
    }

    public String getName_Customer() {
        return name_Customer;
    }

    public void setName_Customer(String name_Customer) {
        this.name_Customer = name_Customer;
    }

    @Override
    public String toString() {
        return "Invoice{" + "num_Customer=" + num_Customer + ", date_Customer=" + date_Customer + ", name_Customer=" + name_Customer + '}';
    }

    public ArrayList<LineOfInvoice> getLines() {
        //nif lines has't items
        if (lines == null)
        {
            lines = new ArrayList<>();
        }
        return lines;
    }

    public void setLines(ArrayList<LineOfInvoice> lines) {
        this.lines = lines;
    }

    public String getAsCSV(){
        return num_Customer + "," + date_Customer + "," + name_Customer;
    }
   
    
}
