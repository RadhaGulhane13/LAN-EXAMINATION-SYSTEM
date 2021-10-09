/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author RADHA
 */
public class Client extends JFrame implements ActionListener {

    JLabel lbl1, qField, lbl2;
    JRadioButton rd1, rd2, rd3, rd4;
    ButtonGroup bg;
    JButton next;

    String username;
    String password;
    JTextField txtuser;
    JPasswordField pass;
    JLabel luser;
    JLabel lpass;
    JButton blogin;
    JFrame fm;

    Socket s;
    DataInputStream in;
    DataOutputStream out;

    String marks;

    Client() throws UnknownHostException, IOException {
        /* Each client will be redirect to the Login Page*/
        LoginPage();
    }

    public void LoginPage() {

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

    public void ErrorPage() {
        fm = new JFrame();
        fm.setVisible(true);
        fm.setLocation(700, 350);
        fm.setLayout(null);
        fm.setSize(500, 200);
        fm.setTitle("Result");

        luser = new JLabel("Invalid User");
        fm.add(luser);
        luser.setBounds(200, 50, 150, 30);

    }

    @Override
    @SuppressWarnings("empty-statement")
    public void actionPerformed(ActionEvent e) {
        username = txtuser.getText();
        password = new String(pass.getPassword());

        try {

            int port = 5555;
            /*
            An IP address is a unique address that identifies a device on the 
            internet or a local network.
            get desktops ipaddress in network...ethernet
            get desktops ipaddress in network...if connnetcted to router 
            https://www.youtube.com/watch?v=4r4qm_Zxnik
            */
            s = new Socket(InetAddress.getLocalHost(), port);
            //System.out.println("localhost add..."+InetAddress.getLocalHost());
            System.out.println("Client is requesting...");
            in = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());
            
            /* Send username and password to the server */
            out.writeUTF(username);
            out.flush();
            out.writeUTF(password);
            out.flush();
            System.out.println("Calling give test");
            fm.setVisible(false);
            if (in.readUTF().equals("ABORT")) {
                ErrorPage();
                disconnect();

            } else {
                /* 
                Once the username and passward is validated by server,
                student will be redirected to the test screen.
                */
                
                testScreen();
            }

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    Once the student done with answering all quetions, this function call gets
    called which will display marks scored by the student.
    */
    void displayMarks() {
        fm = new JFrame();
        fm.setVisible(true);
        fm.setLocation(700, 350);
        fm.setLayout(null);
        fm.setSize(500, 200);
        fm.setTitle("Result");

        luser = new JLabel("Username : " + username);
        JLabel lmarks = new JLabel("Marks : " + marks + " out of 10 ");
        fm.add(luser);
        fm.add(lmarks);
        luser.setBounds(200, 50, 150, 30);
        lmarks.setBounds(200, 70, 150, 30);
    }
    
    /*
    Read the questions and options sent by the server and and send the answer
    selected by student to the server
    */
    void iterator(ActionEvent e) throws IOException {
        if (rd1.isSelected()) {
            out.writeUTF("a");
        } else if (rd2.isSelected()) {
            out.writeUTF("b");
        } else if (rd3.isSelected()) {
            out.writeUTF("c");
        } else if (rd4.isSelected()) {
            out.writeUTF("d");
        } else {
            out.writeUTF("e");
        }

        bg.clearSelection();

        String data = "";
        data = in.readUTF();

        if (data != null) {
            if (data.equals("over")) {
                /* Read the marks sent by the server. */
                marks = in.readUTF();
                fm.setVisible(false);
                displayMarks();
                //System.out.println("Marks: "+marks);
                disconnect();
                return;
            }
            
            /* Read the quetion and option provided by server and display it on 
            testscreeen */
            String[] qform;
            qform = data.split("\\|");
            System.out.println(qform[0] + qform[1] + qform[2] + qform[3] + qform[4]);
            qField.setText(qform[0]);
            rd1.setText(qform[1]);
            rd2.setText(qform[2]);
            rd3.setText(qform[3]);
            rd4.setText(qform[4]);

        }
    }

    void testScreen() throws IOException {
        fm = new JFrame();
        fm.setVisible(true);
        fm.setLocation(650, 250);
        fm.setLayout(null);
        fm.setSize(800, 800);
        fm.setTitle("Test");

        lbl1 = new JLabel("Question :");
        lbl1.setBounds(100, 70, 100, 30);
        fm.add(lbl1);

        qField = new JLabel();
        qField.setBounds(200, 70, 400, 30);
        fm.add(qField);

        lbl2 = new JLabel("Options :");
        lbl2.setBounds(100, 160, 100, 30);
        fm.add(lbl2);

        rd1 = new JRadioButton("a");
        rd1.setBounds(200, 200, 350, 50);
        fm.add(rd1);

        rd2 = new JRadioButton("b");
        rd2.setBounds(200, 250, 350, 50);
        fm.add(rd2);

        rd3 = new JRadioButton("c");
        rd3.setBounds(200, 300, 350, 50);
        fm.add(rd3);

        rd4 = new JRadioButton("d");
        rd4.setBounds(200, 350, 350, 50);
        fm.add(rd4);

        bg = new ButtonGroup();
        bg.add(rd1);
        bg.add(rd2);
        bg.add(rd3);
        bg.add(rd4);

        next = new JButton("NEXT");
        next.setBounds(600, 600, 100, 50);
        fm.add(next);

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    iterator(e);
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //Scanner sin = new Scanner(System.in);
        String data = "";
        data = in.readUTF();

        if (data != null) {
            if (data.equals("over")) {
                //make fm invisible n marks show
                marks = in.readUTF();

                System.out.println("Marks: " + marks);
                fm.setVisible(false);
                disconnect();
                return;
            }
            String[] qform;
            qform = data.split("\\|");
        
            qField.setText(qform[0]);
            rd1.setText(qform[1]);
            rd2.setText(qform[2]);
            rd3.setText(qform[3]);
            rd4.setText(qform[4]);

        }

    }

    void disconnect() throws IOException {
        in.close();
        out.close();
        s.close();
        System.out.println("Client diconnected successfully...");
    }

    public static void main(String[] args) throws IOException {
        /* Create new client  */
        Client c = new Client();
    }

}
