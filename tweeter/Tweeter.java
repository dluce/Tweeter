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

    //global variables
    private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private static String user;
    private static User current;
    
    /**
     * 
     * @param args The command line arguments
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException{
        //introduction
        System.out.println("Welcome to Tweeter! Would you like to login, register,"
                + "or browse public Tweets? ");
        System.out.println("L: Login\n"
                + "R: Register\n"
                + "B: Browse");
        
        //Interpret input as a char
        char in = charIn();
        boolean next;
        
        switch (in) {
            case 'l' : next = login();
                break;
            case 'r' : next = register();
                break;
            default  : next = false;
                break;
        }

        while(next){
            char c;
            System.out.println("What would you like to do?\n");
            System.out.println("E: Edit Profile Information");
            System.out.println("P: View Posts from Subscribed");
            System.out.println("F: View a Friend's Profile");
            System.out.println("A: Add a Friend");
            System.out.println("Q: Quit");
            c = charIn();

            if(c == 'e'){
                editProfile();
            }else if(c == 'p'){
                viewPosts();
            }
            else if(c == 'f'){
                viewFriend();
            }
            else if(c == 'a'){
                addFriend();
            }
            else if(c == 'q'){
                break;
            }
        }
        
        
    }

    private static void editProfile() throws SQLException {
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

            c = charIn();
            Conn db = new Conn();

            if(c == 'f'){
                System.out.println("What is your first name?");
                s = stringIn();
                //save to database
                db.insertQuery("UPDATE profile SET fname = '"+ s 
					+ "' WHERE id = " + current.getId() + ";");
            }
            else if(c == 'l'){
                System.out.println("What is your last name?");
                s = stringIn();
                //save to database
                db.insertQuery("UPDATE profile SET lname = '"+ s 
					+ "' WHERE id = " + current.getId() + ";");
            }
            else if(c == 'e'){
                System.out.println("What is your email address?");
                s = stringIn();
                //save database
                db.insertQuery("UPDATE profile SET email = '"+ s 
					+ "' WHERE id = " + current.getId() + ";");
            }
            else if(c == 'g'){
                System.out.println("What is your gender?");
                s = stringIn();
                //save to database
                db.insertQuery("UPDATE profile SET gender = '"+ s 
					+ "' WHERE id = " + current.getId() + ";");
            }
            else if(c == 'r'){
                System.out.println("What is your relationship status?");
                s = stringIn();
                //save to database
                db.insertQuery("UPDATE profile SET rltnship = '"+ s 
					+ "' WHERE id = " + current.getId() + ";");
            }
            else if(c == 'p'){
                System.out.println("Make profile public to anyone? (y/n)");
                if(boolIn()){
                    //save as public
                    db.insertQuery("UPDATE profile SET public_profile = 1 WHERE id = " 
						+ current.getId() + ";");
                }
                else{
                    //save as private
                    db.insertQuery("UPDATE profile SET public_profile = 0 WHERE id = " 
						+ current.getId() + ";");
                }
            }
            else if(c == 'q'){
                return;
            }
            else{
                System.out.println("Invalid Input");
            }

        }

    }

	/**
     * Prints all posts from users to which current is subscribed
     * @throws SQLException 
     */
    private static void viewPosts() throws SQLException{
           //print posts from everyone
    }

    private static void viewSubscript() throws SQLException{

    }	
        
    private static boolean register() throws SQLException{
        return true;
    }
    
    /**
     * Prints all posts from users to which current is subscribed
     * @throws SQLException 
     */
    private static void viewPosts() throws SQLException{
           //print subscriptions
    }

    private static void viewFriend() throws SQLException{
        String s;
        System.out.println("Which friend's profile would you like "
                + "to see (search by username)? ");
        s = stringIn();
        
        Conn db = new Conn();
        ResultSet rs;
        rs = db.searchQuery("SELECT u.username, p.fname, p.lname, p.email, p.gender, p.rltnship "
                + "FROM users_330 AS u "
                + "INNER JOIN profile AS p "
                + "WHERE u.id = "
                + current.getId()
                + " AND p.id = "
                + current.getId() + ";");
        
        //should only have one row result
        //print out profile information of the given user
        while(rs.next()){
            String temp = rs.getString("username");
            System.out.println(temp);
            temp = rs.getString("fname");
            System.out.println(temp);
            temp = rs.getString("lname");
            System.out.println(temp);
            temp = rs.getString("email");
            System.out.println(temp);           
            temp = rs.getString("gender");
            System.out.println(temp);
            temp = rs.getString("rltnship");
            System.out.println(temp);        
        }
        
        rs.close();
        
        //search and print that friend's profile
    }
    
    /**
     * Adds a subscription to currently logged in user
     * @throws SQLException 
     */
    private static void addFriend() throws SQLException {
        
        //ask user for input
        String name;
        System.out.println("What is the username of the person you want to subscribe to?");
        name = stringIn();
        
        //connect to the database to execute a query
        Conn db = new Conn();
        ResultSet rs;
        rs = db.searchQuery("SELECT u.id FROM users_330 AS u WHERE username = " + name);
        while(rs.next()){
            db.insertQuery("INSERT INTO subs (o_user, s_user) VALUES (" 
                    + current.getId() + ", " 
                    + rs.getInt("id") + ")");       
        }
        
        System.out.println("Sorry, that user does not exist.");


    }

    private static boolean login() throws SQLException {
        String password;

        while(true){
            System.out.println("Are you a registered member?");

            if(boolIn()){
                //get username   
                System.out.println("Enter username:");
                user = stringIn();
                
                //get password
                System.out.println("What is your password?");
                password = stringIn();
                
                //validate
                
                Conn db = new Conn();
                ResultSet rs;
                rs = db.searchQuery("SELECT * FROM users_330 WHERE "
                        + "username = " + user 
                        + "AND password = " + password + ";");
                
                if(rs.next()){
                    System.out.println("Welcome " + user);
                    current = new User(user);
                    return true;
                }
                else{
                    continue;
                }
            }

            else{
                while(true){

                    System.out.println("What username would you like to use?");
                    //make sure it is not in use  if(!)
                    System.out.println("What would you like your password to be?");
                    password = stringIn();
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
            
            if(stringIn.length()>0){//block error from empty return
                charIn = stringIn.charAt(0); //add full word functionality
            }
            //translate english to boolean
            if(charIn=='y'){
                return true;
            }
            else if(charIn=='n'){
                return false;
            }
            else {
                System.out.println("Invalid Input. Must use 'y' or 'n'.");
            }
        }
    }
    
    /**
     * Gains input from the user as needed.
     * 
     * @return Most recent input string from the user
     */
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

            if(stringIn.length() > 0){ //block error from empty return
                return stringIn;
            }
            //if user didn't enter anything, they try again.
            System.out.println("Invalid Input. Please try again.");
        }
    }
    
    /**
     * 
     * @return Allows for capitalized letters when choosing menu options.
     */
    private static char charIn(){
        String s = stringIn();
        char c;

        s = s.toLowerCase(); //add capitalized input functionality
        c = s.charAt(0);

        return c;
    }
}