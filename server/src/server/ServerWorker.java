package server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author RADHA
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import static java.lang.System.in;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//add marks according to thread name
//send thread number from client along with client's answer
//all clients are getting jumbled
public class ServerWorker extends Thread {

    Socket worker;
    DataInputStream in;
    DataOutputStream out;
    ArrayList<Question> qset;
    Database db;
    //String username,password;
    int ID;

    public ServerWorker(Socket worker, Database db, int ID) throws ClassNotFoundException, SQLException {
        this.worker = worker;
        this.db = db;
        qset = db.fetch();
        this.ID = ID;
    }

    @Override
    public void run() {
        try {

            handleCheck();

        } catch (IOException ex) {
            Logger.getLogger(ServerWorker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerWorker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServerWorker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void handleCheck() throws IOException, InterruptedException, SQLException {
        int marks = 0;
        in = new DataInputStream(worker.getInputStream());
        out = new DataOutputStream(worker.getOutputStream());

        String str = "";

        int count = 0;
        System.out.println("inside handlercheck");
        while (true) {
            if (count == 10) {
                System.out.println("count:" + count);
                out.flush();
                out.writeUTF("over");

                out.flush();
                out.writeUTF(Integer.toString(marks));

                break;
            }
            //out.writeUTF("Question : "+q.getQues()+"\n"+q.opt[0]+"\t"+q.opt[1]+"\t"+q.opt[2]+"\t"+q.opt[3]);
            //System.out.println("Question : "+qset.get(count).getQues()+"\n"+qset.get(count).opt1+"\t"+qset.get(count).opt2+"\t"+qset.get(count).opt3+"\t"+qset.get(count).opt4);

            out.writeUTF(qset.get(count).getQues() + "|" + qset.get(count).opt1 + "|" + qset.get(count).opt2 + "|" + qset.get(count).opt3 + "|" + qset.get(count).opt4);
            out.flush();

            str = (String) in.readUTF();
            System.out.print("ans :" + str + "   ");
            if (str.equals(qset.get(count).getAns())) {
                marks++;
                System.out.println("Wow Answer is correct...");

            } else {
                System.out.println("OOhhhoo sorry Answer is incorrect...");
            }

            count++;
        }

        // Thread.sleep(500);
        System.out.println("marks : " + marks + "\n");
        db.setMarks(marks, ID);
        in.close();
        out.close();
        worker.close();
        System.out.println("here in worker");

    }
}
