/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author RADHA
 */
import java.awt.Color;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Test extends JFrame {

    public Test() {
        
        /*  Graphical User Interface for Test   */

        this.setVisible(true);
        this.setLayout(null);

        this.setTitle("Test");
        this.setLocation(650, 250);
        this.setSize(800, 800);

        JLabel lbl1;
        lbl1 = new JLabel("Question :");
        lbl1.setBounds(100, 70, 100, 30);
        this.add(lbl1);

        JTextField qField = new JTextField();
        qField.setBounds(200, 65, 250, 30);
        this.add(qField);

        JLabel lbl2;
        lbl2 = new JLabel("Options :");
        lbl2.setBounds(100, 160, 100, 30);
        this.add(lbl2);

        JRadioButton rd1 = new JRadioButton("a");
        rd1.setBounds(200, 200, 100, 30);

        this.add(rd1);

        JRadioButton rd2 = new JRadioButton("b");
        rd2.setBounds(200, 250, 100, 30);
        this.add(rd2);

        JRadioButton rd3 = new JRadioButton("c");
        rd3.setBounds(200, 300, 100, 30);
        this.add(rd3);

        JRadioButton rd4 = new JRadioButton("d");
        rd4.setBounds(200, 350, 100, 30);
        this.add(rd4);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rd1);
        bg.add(rd2);
        bg.add(rd3);
        bg.add(rd4);

        JButton next = new JButton("NEXT");
        next.setBounds(600, 600, 100, 50);
        this.add(next);

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new Test();
    }

}
