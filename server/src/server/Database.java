package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author RADHA
 */

/*
* Database will store all  information about the students as well as the 
* questions and answers for the test will be stored in database and server 
* will access this information from database using functions provided by this 
* class.
*/

public class Database {
    Connection con;
    ResultSet rs;
    
    /* Create instance of database and connect with it. */
    Database() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        System.out.println("Driver loaded successfully.....");
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "RADHA_2", "RADHA");
        //con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","DATABASE","PASSWORD");
        System.out.println("Connection Establish.......");

    }
    /* Fetch all quetions and answers from the database. */
    public ArrayList<Question> fetch() throws SQLException {
        Statement stmt = con.createStatement();
        rs = stmt.executeQuery("select * from Question");
        ArrayList<Question> qset = new ArrayList<>();
        while (rs.next()) {
            //System.out.println(rs.getString(2)+rs.getString(3)+rs.getString(4)+rs.getString(5)+rs.getString(6)+rs.getString(7));
            Question q = new Question(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
            qset.add(q);
        }

        return qset;
    }
    
    /* Validates user */
    public int isvaliduser(String username, String password) throws SQLException {
        //System.out.println(username+"  "+password);
        Statement stmt = con.createStatement();
        rs = stmt.executeQuery("select * from Student");

        while (rs.next()) {
            //  System.out.println(rs.getString(2)+rs.getString(3));
            if (username.equals(rs.getString(2)) && password.equals(rs.getString(3))) {
                return rs.getInt(1);

            }
        }

        return 0;
    }
    
    /* Update the marks of students in database. */
    public void setMarks(int marks, int ID) throws SQLException {
        //   Statement stmt = con.createStatement();
        String sql = "UPDATE STUDENT SET MARKS=? WHERE ID=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, marks);
        pstmt.setInt(2, ID);
        pstmt.executeUpdate();
        //System.out.println("in db :"+ID);

        //stmt.executeUpdate(sql);
    }

}

/* Comment out below code if you do not want to use the database functionality.
 * In such case, the qustions will be fixed and harcoded explicitly.
 *
 */ 
//
//package server;
//
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
///**
// *
// */
//public class Database {
//    Connection con;
//    ResultSet rs;
//    
//    Database() throws ClassNotFoundException, SQLException
//    {
//       //Class.forName("oracle.jdbc.driver.OracleDriver");
//	System.out.println("Driver loaded successfully.....");
//	//con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "RADHA", "Radha123$");
//		
//	System.out.println("Connection Establish.......");
//		
//		 
//    }
//    public ArrayList<Question>fetch() throws SQLException
//    {
//        //Statement stmt = con.createStatement();
//	//rs=stmt.executeQuery("select * from Question");  
//         ArrayList<Question> qset = new ArrayList<>();
//         
//      //       System.out.println(rs.getString(2)+rs.getString(3)+rs.getString(4)+rs.getString(5)+rs.getString(6)+rs.getString(7));
//             Question q = new Question("which language is fully object oriented language?","CPP","C","JAVA","None of the above","c");
//             qset.add(q);
//             
//             q = new Question("which language does not support object oriented features??","CPP","C","JAVA","None of the above","b");
//             qset.add(q);
//             
//             q = new Question("Wrapping data and its related functionality into a single entity is known as _____________?","Abstraction"," Encapsulation","Polymorphism","Modularity","b");
//             qset.add(q);
//             
//               q = new Question("hjhkjhkjhk ypu ?","axmk","jcj","jk","jk","b");
//             qset.add(q);
//             
//               q = new Question("hjhkjhkjhk ypu ?","axmk","jcj","jk","jk","b");
//             qset.add(q);
//             
//               q = new Question("hjhkjhkjhk ypu ?","axmk","jcj","jk","jk","b");
//             qset.add(q);
//             
//               q = new Question("hjhkjhkjhk ypu ?","axmk","jcj","jk","jk","b");
//             qset.add(q);
//             
//               q = new Question("hjhkjhkjhk ypu ?","axmk","jcj","jk","jk","b");
//             qset.add(q);
//             
//               q = new Question("hjhkjhkjhk ypu ?","axmk","jcj","jk","jk","b");
//             qset.add(q);
//               q = new Question("which language is fully object oriented language?","CPP","C","JAVA","None of the above","JAVA");
//             qset.add(q);
//	return qset;	      
//        
//    }
//    
//    public int isvaliduser(String username,String password) throws SQLException
//    {
//       /* Statement stmt = con.createStatement();
//	rs=stmt.executeQuery("select * from Student");
//        
//        while(rs.next())
//        {
//            System.out.println(rs.getString(2)+rs.getString(3));
//          if(username.equals(rs.getString(2))&& password.equals(rs.getString(3)))
//            {
//                return rs.getInt(1);
//                
//            }
//        }*/
//   
// 
//         return 1;
//    }
//    
//    public void setMarks(int marks,int ID) throws SQLException
//    {/*
//     //   Statement stmt = con.createStatement();
//       String sql="UPDATE STUDENT SET MARKS=? WHERE ID=?";
//        PreparedStatement pstmt = con.prepareStatement(sql);
//        pstmt.setInt(1,marks);
//        pstmt.setInt(2,ID);
//        pstmt.executeUpdate();
//        //System.out.println("in db :"+ID);
//        
//        //stmt.executeUpdate(sql);
//*/
//    }
//         
//   
//}
