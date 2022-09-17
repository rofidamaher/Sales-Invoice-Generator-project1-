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
public class Lines_T_M extends AbstractTableModel{
    
    private ArrayList<LineOfInvoice> lin ; 
    private String[] col = {"No." , "Item Name " , "Item Price " , "Count" , "Item Total "};

    public Lines_T_M(ArrayList<LineOfInvoice> lin) {
        this.lin = lin;
    }
 
    
    @Override
    public int getRowCount() {
        return lin.size();
    }

    @Override
    public int getColumnCount() {
        return col.length;
    }

    @Override
    public String getColumnName(int column) {
         return col[column];     
    } 
   
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         LineOfInvoice line = lin.get(rowIndex);
       switch (columnIndex) {
            case 0: return line.getInvoice().getNum_Customer();
            case 1: return line.getItemName();
            case 2: return line.getItemPrice();
            case 3: return line.getItemCount();
            case 4: return line.getTotalCount();
            default : return "";
                
        }
    
    }
    
    
    
}
