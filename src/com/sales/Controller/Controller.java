/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sales.Controller;

import com.sales.Model.Invoice;
import com.sales.Model.LineOfInvoice;
import com.sales.View.Invoice_Frame_Form;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.JFileChooser;
import java.lang.String;
import java.util.ArrayList;

public class Controller implements ActionListener {
    private Invoice_Frame_Form frame_form;
    
    public Controller(Invoice_Frame_Form frame_form) {
        this.frame_form = frame_form;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommandString = e.getActionCommand();
        
       System.out.println(" Action " + actionCommandString);
       
       switch(actionCommandString)
       {
           case "Load_File":
               Load_File();
               break;
          case "Save_File":
               Save_File();
               break;
          case "Create_New_Invoice":
               Create_New_Invoice();
               break;
          case "Delete_Invoice":
               Delete_Invoice();
               break;
          case "Create_New_Item":
               Create_New_Item();
               break;
          case "Delete_Item":
               Delete_Item();
               break;
       }
       
    }

    private void Load_File() {
        JFileChooser f_c = new JFileChooser();
        try{
        int res = f_c.showOpenDialog(frame_form);
        if (res == JFileChooser.APPROVE_OPTION)
        {
            File file = f_c.getSelectedFile();
            Path h_path = Paths.get(file.getAbsolutePath());
            List<String> h_lines = Files.readAllLines(h_path);

            System.out.print(" Invoices had been read ");
            ArrayList<Invoice> invoicesArr = new ArrayList();
            for (String h_line : h_lines ){
                String[] h_parts = h_line.split(",");
                int  i_Num =Integer.parseInt( h_parts[0]);
                String i_Date =  h_parts[1];
                String i_CustomerName =  h_parts[2];
                Invoice invoice = new Invoice(i_Num , i_Date ,i_CustomerName);
                invoicesArr.add(invoice);
                             
            }
            System.out.print("invoicesArr");
             res = f_c.showOpenDialog(frame_form);
            if (res == JFileChooser.APPROVE_OPTION)
        {
            File line_File = f_c.getSelectedFile();
            Path L_path = Paths.get(line_File.getAbsolutePath());
            List<String> L_lines = Files.readAllLines(L_path);

            System.out.print(" Lines had been read ");
            for (String L_line : L_lines ){
                String[] h_parts = L_line.split(",");
                int  i_Num =Integer.parseInt( h_parts[0]);
                String l_Item_Name =  h_parts[1];
                double l_Item_Price = Double.parseDouble(h_parts[2]);
                int l_Count = Integer.parseInt( h_parts[3]);
                Invoice inv = null;
                for (Invoice invoice :invoicesArr){
                    if ( invoice.getNum_Customer() == i_Num ){
                        inv = invoice;
                        break;
                    }
                }
               LineOfInvoice l = new LineOfInvoice(i_Num,l_Item_Name,l_Item_Price,l_Count,inv);
               inv.getLines().add(l);                  
            }
            System.out.print(" items had been load  ");
        }
          frame_form.setInvoices(invoicesArr);
        }
        
        
          }catch(IOException e){
              e.printStackTrace();
          }
    }
    private void Save_File() {
          }

    private void Create_New_Invoice() {
        }

    private void Delete_Invoice() {
         }

    private void Create_New_Item() {
       }

    private void Delete_Item() {
        }
    
}
