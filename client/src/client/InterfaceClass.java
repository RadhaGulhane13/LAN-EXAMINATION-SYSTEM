/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author RADHA
 */
public class InterfaceClass extends JFrame implements ActionListener {

    String username;
    String password;
    JTextField txtuser;
    JPasswordField pass;
    JLabel luser;
    JLabel lpass;
    JButton blogin;
    JFrame fm;

    public void LoginPage() {
        
        /* Graphical User Interface for Login Page */

        fm = new JFrame();
        fm.setVisible(true);
        fm.setLocation(700, 350);
        fm.setLayout(null);
        fm.setSize(550, 300);
        fm.setTitle("Login");

        luser = new JLabel("User Name : ");
        fm.add(luser);
        luser.setBounds(130, 60, 150, 30);

        txtuser = new JTextField();
        fm.add(txtuser);
        txtuser.setBounds(270, 60, 150, 30);

        lpass = new JLabel("Password   : ");
        fm.add(lpass);
        lpass.setBounds(130, 100, 150, 30);

        pass = new JPasswordField();
        fm.add(pass);
        pass.setBounds(270, 100, 150, 30);

        blogin = new JButton("Login");
        fm.add(blogin);
        blogin.setBounds(200, 200, 150, 30);
        blogin.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        username = txtuser.getName();
        password = pass.getPassword().toString();

        System.out.println("Inactionper");
    }

}
