
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

/**
 *
 * @author RADHA
 */
public class Server {

    public static void main(String[] args) {
        try {
            int port = 5555;

            ServerSocket ss = new ServerSocket(5555);
            System.out.println("Server is created...");
            while (true) {
                Socket s = ss.accept();//establishes connection
                System.out.println("Network is established between client and server...");
                String u, pass = "";
                int ID;
                DataInputStream in = new DataInputStream(s.getInputStream());
                DataOutputStream out = new DataOutputStream(s.getOutputStream());
                u = in.readUTF();

                pass = in.readUTF();
                Database db = new Database();

                System.out.println("up: " + u + "  " + pass);
                ID = db.isvaliduser(u, pass);
                if (ID != 0) {
                    System.out.println("in if");
                    out.writeUTF("START");
                    
                    /* Create at thread for each student/ client. */
                    ServerWorker worker = new ServerWorker(s, db, ID);
                    System.out.println("going into worker");
                    worker.start();

                } else {
                    System.out.println("in else");

                    // errorPage();
                    out.writeUTF("ABORT");

                    out.flush();
                    db.con.close();
                    in.close();
                    out.close();
                    s.close();
                }

            }
            //System.out.println("Server diconnected successfully...");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
