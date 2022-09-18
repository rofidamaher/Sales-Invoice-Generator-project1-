/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sales.View;


import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class Invoice_Dialog extends JDialog {
    private JTextField c_Name;
    private JTextField inv_Date;
    private JLabel c_Name_L;
    private JLabel inv_Date_L;
    private JButton okBtn;
    private JButton cancelBtn;

    public Invoice_Dialog(Invoice_Frame_Form frame) {
        c_Name_L = new JLabel("Customer Name:");
        c_Name = new JTextField(20);
        inv_Date_L = new JLabel("Invoice Date:");
        inv_Date = new JTextField(20);
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        okBtn.setActionCommand("c_Invo_OK");
        cancelBtn.setActionCommand("c_Invo_Cancel");
        
        okBtn.addActionListener(frame.getController());
        cancelBtn.addActionListener(frame.getController());
        setLayout(new GridLayout(3, 2));
        
        add(inv_Date_L);
        add(inv_Date);
        add(c_Name_L);
        add(c_Name);
        add(okBtn);
        add(cancelBtn);
        
        pack();
        
    }

    public JTextField getC_Name() {
        return c_Name;
    }

    public JTextField getInv_Date() {
        return inv_Date;
    }

  
    
}
