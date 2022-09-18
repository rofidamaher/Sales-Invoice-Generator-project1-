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
 
public class line_Dialog extends JDialog{
    private JTextField item_Name;
    private JTextField item_Count;
    private JTextField item_Price;
    private JLabel item_Name_L;
    private JLabel item_Count_L;
    private JLabel item_Price_L;
    private JButton ok_Btn;
    private JButton cancel_Btn;
    
    public line_Dialog(Invoice_Frame_Form frame) {
        item_Name = new JTextField(20);
        item_Name_L = new JLabel("Item Name");
        
        item_Count = new JTextField(20);
        item_Count_L = new JLabel("Item Count");
        
        item_Price = new JTextField(20);
        item_Price_L = new JLabel("Item Price");
        
        ok_Btn = new JButton("OK");
        cancel_Btn = new JButton("Cancel");
        
        ok_Btn.setActionCommand("c_Line_OK");
        cancel_Btn.setActionCommand("c_Line_Cancel");
        
        ok_Btn.addActionListener(frame.getController());
        cancel_Btn.addActionListener(frame.getController());
        setLayout(new GridLayout(4, 2));
        
        add(item_Name_L);
        add(item_Name);
        add(item_Count_L);
        add(item_Count);
        add(item_Price_L);
        add(item_Price);
        add(ok_Btn);
        add(cancel_Btn);
        
        pack();
    }

    public JTextField getItem_Name() {
        return item_Name;
    }

    public JTextField getItem_Count() {
        return item_Count;
    }

    public JTextField getItem_Price() {
        return item_Price;
    }

    
}
