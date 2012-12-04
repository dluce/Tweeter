/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tweeter;

import java.sql.*;

/**
 *
 * @author David
 */
public class Conn {
    
    //default constructor, gets the initial DB connection
    Conn(){
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver") ;

            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/twitter?"
                + "user=dluce&password=gurren5");
            

        
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
    public ResultSet searchQuery(String s){   
        try{    
            stmt = con.createStatement();
            rs = stmt.executeQuery(s);
            return rs;
        } catch (SQLException ex){
            System.out.println("Exception: " + ex);
            return null;
        }   
    }
    
    public void insertQuery(String s){
        try{
            stmt = con.createStatement();
            err = stmt.executeUpdate(s);
        }
        catch(SQLException e){
            System.out.println("Error Status: " + err);
        }
        
    }
    
    public void close(){
        
        
    }
    
    private Connection con;
    public ResultSet rs;
    private Statement stmt;
    private int err;
}
