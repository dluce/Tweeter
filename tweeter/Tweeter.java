/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tweeter;


import java.sql.*;
import java.io.*;
/**
 *
 * @author David
 */
public class Tweeter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Connection con;
        try {
//            System.out.println("Before loading drivers: ");
//            listDrivers();
// Load the MySQL JDBC driver
        Class.forName("com.mysql.jdbc.Driver") ;
        System.out.println("MySQL JDBC driver loaded ok.");
        
        con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/twitter?"
            + "user=dluce&password=gurren5");
        
        System.out.println("Connected with host:port/database.");
        con.close();
        
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
        
        
        
    }
//    private static void listDrivers() {
//        Enumeration driverList = DriverManager.getDrivers();
//        while (driverList.hasMoreElements()) {
//            Driver driverClass = (Driver) driverList.nextElement();
//            System.out.println("   "+driverClass.getClass().getName());
//        }
//    }
}
