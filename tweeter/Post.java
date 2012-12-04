package tweeter;

import java.sql.Timestamp;
/**
 *
 * @author David
 */
public class Post {
    
    Post(String c, User u, Timestamp ts, String t){
        content = c;
        isPublic = true;
        time = ts;
        user = u.getName();
        
        if(t.equals("")){
            topic = null;
        }              
    }
    
    public void makePrivate(){
        isPublic = false;
    }
    
    public void print(){
        System.out.print(user + " says: " + content);
        
    }
    public String content;
    public String user;
    public boolean isPublic;
    public Timestamp time;
    public String topic;
}
