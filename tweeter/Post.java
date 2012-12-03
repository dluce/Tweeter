package tweeter;

import java.sql.Timestamp;
/**
 *
 * @author David
 */
public class Post {
    
    Post(User u, Timestamp ts, String t){
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
    
    public String content;
    public String user;
    public boolean isPublic;
    public Timestamp time;
    public String topic;
}
