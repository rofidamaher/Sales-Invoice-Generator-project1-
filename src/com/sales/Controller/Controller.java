/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sales.Controller;

import com.sales.Model.Invoice;
import com.sales.Model.InvoicesT_Model;
import com.sales.Model.LineOfInvoice;
import com.sales.Model.Lines_T_M;
import com.sales.View.Invoice_Dialog;
import com.sales.View.Invoice_Frame_Form;
import com.sales.View.line_Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.JFileChooser;
import java.lang.String;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Controller implements ActionListener , ListSelectionListener{
    private Invoice_Frame_Form frame_form;
    private Invoice_Dialog invo_D ;
    private line_Dialog lin_D;
    
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
          case "c_Invo_OK":
              c_Invo_OK();
              break;
          case "c_Invo_Cancel":
              c_Invo_Cancel();
              break;
          case "c_Line_OK":
              c_Line_OK();
              break;
          case "c_Line_Cancel":
              c_Line_Cancel();
              break;
              
       }
       
    }

     @Override
     public void valueChanged(ListSelectionEvent e) {
         int selectIndex = frame_form.getTable_Invoice().getSelectedRow();
         if (selectIndex != -1)
         {
         System.out.println("You have Selected row :" + selectIndex);
         Invoice currentInvo  = frame_form.getInvoices().get(selectIndex);
         frame_form.getInvNumLbl().setText(""+currentInvo.getNum_Customer());
         frame_form.getInvDateLbl().setText(currentInvo.getDate_Customer());
         frame_form.getInvNameLbl().setText(currentInvo.getName_Customer());
         frame_form.getInvTotalLbl().setText(""+currentInvo.getTotalForAllItems());
         Lines_T_M lines_T_M = new Lines_T_M(currentInvo.getLines());
         frame_form.getInvoice_Line().setModel(lines_T_M);
         lines_T_M.fireTableDataChanged();
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
          InvoicesT_Model invo_T_M = new InvoicesT_Model(invoicesArr);
          frame_form.setInv_T_M(invo_T_M);
          frame_form.getTable_Invoice().setModel(invo_T_M);
          frame_form.getInv_T_M().fireTableDataChanged();
          
          
        }
        
        
          }catch(IOException e){
              e.printStackTrace();
          }
    }
    private void Save_File() {
        ArrayList<Invoice> invo = frame_form.getInvoices();
        String h = "";
        String l ="";
        for( Invoice inv :invo){
            String invCSV = inv.getAsCSV();
            h += invCSV;
            h+="\n";
            for( LineOfInvoice lin : inv.getLines()){
                String lCSV = lin.getAsCSV();
                l += lCSV;
                l += "\n";    
        }    
        }
      // System.out.println("jkhjh");
        try{
       JFileChooser f_S = new JFileChooser();
       int res= f_S.showSaveDialog(frame_form);
       if (res == JFileChooser.APPROVE_OPTION)
        {
            File h_File = f_S.getSelectedFile();
            FileWriter f_w = new FileWriter(h_File); 
            f_w.write(h);
            f_w.flush();
            f_w.close();
            
            res= f_S.showSaveDialog(frame_form);
            if (res == JFileChooser.APPROVE_OPTION){
                File L_File = f_S.getSelectedFile();
                FileWriter l_w = new FileWriter(L_File); 
                l_w.write(l);
                l_w.flush();
                l_w.close();
            }
    
        }
        }catch(Exception e ){
            
        }
    }

    private void Create_New_Invoice() {
        invo_D = new Invoice_Dialog(frame_form);
        invo_D.setVisible(true);
        }

    private void Delete_Invoice() {
        int s_Row = frame_form.getTable_Invoice().getSelectedRow();
        if (s_Row != -1 ){
            frame_form.getInvoices().remove(s_Row);
            frame_form.getInv_T_M().fireTableDataChanged();
        }
    }

    private void Create_New_Item() {
        lin_D = new line_Dialog(frame_form);
        lin_D.setVisible(true);
        
       }

    private void Delete_Item() {
        
        int s_Row = frame_form.getInvoice_Line().getSelectedRow();
         if ( s_Row != -1 ){
             Lines_T_M line_T_M = (Lines_T_M)frame_form.getInvoice_Line().getModel();
            line_T_M.getLin().remove(s_Row);
            line_T_M.fireTableDataChanged();     
        }
         /*
        int s_Invo = frame_form.getTable_Invoice().getSelectedRow();
        if (s_Invo != -1 && s_Row != -1 ){
            Invoice invo = frame_form.getInvoices().get(s_Invo);
            invo.getLines().remove(s_Row);
            Lines_T_M line_T_M = new Lines_T_M(invo.getLines());
            frame_form.getInvoice_Line().setModel(line_T_M);
            line_T_M.fireTableDataChanged();     
        }*/
        }

    private void c_Invo_OK() {
        String d = invo_D.getInv_Date().getText();
        String name = invo_D.getC_Name().getText();
        int n = frame_form.get_Next_InvoNum();
        Invoice invo = new Invoice(n , d , name );
        frame_form.getInvoices().add(invo);
        frame_form.getInv_T_M().fireTableDataChanged();
        invo_D.setVisible(false);
        invo_D.dispose();
        invo_D = null;
    }

    private void c_Invo_Cancel() {
        invo_D.setVisible(false);
        invo_D.dispose();
        invo_D = null;
    }

    private void c_Line_OK() {
        String item_Name = lin_D.getItem_Name().getText();
        int item_Count = Integer.parseInt(lin_D.getItem_Count().getText());
        int item_Price = Integer.parseInt(lin_D.getItem_Price().getText());
        int s = frame_form.getTable_Invoice().getSelectedRow();
        if (s!=-1){
            Invoice inv = frame_form.getInvoices().get(s);
            LineOfInvoice new_Line = new LineOfInvoice(inv.getNum_Customer(),item_Name,item_Count,item_Price,inv);
            inv.getLines().add(new_Line);
            Lines_T_M l_T_M = (Lines_T_M) frame_form.getInvoice_Line().getModel();
            l_T_M.fireTableDataChanged();
            frame_form.getInv_T_M().fireTableDataChanged();
        }
        
        lin_D.setVisible(false);
        lin_D.dispose();
        lin_D = null;
    }

    private void c_Line_Cancel() {
        lin_D.setVisible(false);
        lin_D.dispose();
        lin_D = null;
    }

   
    
}
