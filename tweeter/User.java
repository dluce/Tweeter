package tweeter;
import java.sql.*;
/**
 *
 * @author David
 */
public class User {
    
    User(String n){
        try{
            name = n;
            Conn db = new Conn();
            ResultSet rs = db.searchQuery("SELECT id FROM users_330 "
                    + "WHERE username = '" + name + "';");
            //this should only have one result row.
            while(rs.next()){
                id = rs.getInt("id");
            }
        }
        catch (SQLException e){
            System.out.println("SQL Exception: " + e);
        }
    }
    
    public String getName(){
        return name;
    }
    
    public int getId(){
        return id;
    }
    
    private char gender;
    private String name;
    private int id;
    
}
