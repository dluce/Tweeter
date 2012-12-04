/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tweeter;


import java.sql.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.String;
/**
 *
 * @author David
 */
public class Tweeter {

    /**
     * @param args the command line arguments
     */
    private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private static String user;

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

        login();

        while(true){
            char c;
            System.out.println("What would you like to do?\n");
            System.out.println("E: Edit Profile Information");
            System.out.println("P: View Posts from Subscribed");
            System.out.println("F: View a Friend's Profile");
            System.out.println("A: Add a Friend");
            System.out.println("Q: Quit");
            c=charIn();

            if(c=='e'){
                editProfile();
            }else if(c=='p'){
                viewPosts();
            }
            else if(c=='f'){
                viewFriend();
            }
            else if(c=='a'){
                addFriend();
            }
            else if(c=='q'){
                break;
            }
        }
        
        
    }
//    private static void listDrivers() {
//        Enumeration driverList = DriverManager.getDrivers();
//        while (driverList.hasMoreElements()) {
//            Driver driverClass = (Driver) driverList.nextElement();
//            System.out.println("   "+driverClass.getClass().getName());
//        }
//    }

    private static void editProfile(){
        char c;
        String s;
        while(true){
            //print your profile
            System.out.println("What would you like to edit?\n");
            System.out.println("F: First Name");
            System.out.println("L: Last Name");
            System.out.println("E: Email");
            System.out.println("G: Gender");
            System.out.println("R: Relationship Status");
            System.out.println("P: Make Profile Public to Anyone?");
            System.out.println("Q: I'm done");

            c=charIn();
            if(c=='f'){
                System.out.println("What is your first name?");
                s=stringIn();
                //save to database
            }
            else if(c=='l'){
                System.out.println("What is your last name?");
                s=stringIn();
                //save to database
            }
            else if(c=='e'){
                System.out.println("What is your email address?");
                s=stringIn();
                //save database
            }
            else if(c=='g'){
                System.out.println("What is your gender?");
                s=stringIn();
                //save to database
            }
            else if(c=='r'){
                System.out.println("What is your relationship status?");
                s=stringIn();
                //save to database
            }
            else if(c=='p'){
                System.out.println("Make profile public to anyone? (y/n)");
                if(boolIn()){
                    //save as public
                }
                else{
                    //save as private
                }
            }
            else if(c=='q'){
                return;
            }
            else{
                System.out.println("Invalid Input");
            }

        }

    }

    private static void viewPosts(){
           //print subscription
    }

    private static void viewFriend(){
        String s;
        System.out.println("Which friend's profile would you like to see?");
        s=stringIn();
        //search and print that friend's profile
    }

    private static void addFriend(){
        String f;
        String l;
        System.out.println("What is the first name of the person you wish to add?");
        f=stringIn();
        System.out.println("What is the last name of the person you wish to add?");
        l=stringIn();

        //query



    }

    private static void login(){
        String password="";

        while(true){
            System.out.println("Are you a registered member?");

            if(boolIn()){

                    System.out.println("What is your username?");
                    user=stringIn();
      //------------check if user is valid     if(!){
                        System.out.println("Invalid username.");
                    //else{
                        System.out.println("What is your password?");
                        password=stringIn();
                        //if password is valid
                            System.out.println("Welcome "+user);
                            return;
            }

            else{
                while(true){

                System.out.println("What username would you like to use?");
                //make sure it is not in use  if(!)
                    System.out.println("What would you like your password to be?");
                    password=stringIn();
                    //set username & password
                    break;

                }

            }


        }
    }

    private static boolean boolIn(){
        String stringIn="";
        char charIn= ' ';

        //loop until return
        while(true){
            try{
                stringIn = input.readLine();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            stringIn = stringIn.toLowerCase(); //add capitalized input functionality
            if(stringIn.length()>0)//block error from empty return
                charIn = stringIn.charAt(0); //add full word functionality

            //translate english to boolean
            if(charIn=='y')
                return true;
            else if(charIn=='n')
                return false;
            else
                System.out.println("Invalid Input");
        }
    }

    private static String stringIn(){
        String stringIn="";

        while(true){
            try{
                stringIn = input.readLine();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }

            if(stringIn.length()>0)//block error from empty return
               return stringIn;

            System.out.println("Invalid Input");
        }
    }

    private static char charIn(){
        String s = stringIn();
        char c;

        s = s.toLowerCase(); //add capitalized input functionality
        c = s.charAt(0);

        return c;
    }
}

