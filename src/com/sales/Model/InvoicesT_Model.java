/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sales.Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC_2023
 */
public class InvoicesT_Model extends AbstractTableModel {
    
     private ArrayList<Invoice> inv; 
     private String[] col = {"No." , "Date" , "Customer" , "Total"};

    public InvoicesT_Model(ArrayList<Invoice> inv) {
        this.inv = inv;
    }

     
     
    @Override
    public int getRowCount() {
        return inv.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "No.";
            case 1: return "Date";
            case 2: return "Customer";
            case 3: return "Total";
            default : return "";
                
        }
    }//To change body of generated methods, choose Tools | Templates.
    /*
       public int getColumnCount() {
        return col.length;
    }

    @Override
    public String getColumnName(int column) {
        return col[column];
    }//To change body of generated methods, choose Tools | Templates.
    
    */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       Invoice invo = inv.get(rowIndex);
       switch (columnIndex) {
            case 0: return invo.getNum_Customer();
            case 1: return invo.getDate_Customer();
            case 2: return invo.getName_Customer();
            case 3: return invo.getTotalForAllItems();
            default : return "";
                
        }
    }
    
}
