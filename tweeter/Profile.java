package tweeter;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author David
 */
public class Profile {
    
    Profile(User u){
        user = u;
        
    }
    
    public boolean setPublic(int i){
        if (i == 0){
            isPublic = false;
            return false;
        }
        
        isPublic = true;
        return true;
    }
    
    //might not need this mutator if it ends up being
    //handled entirely by the DB
    public void setRelationship(String s){
        relationship = s;
    }
    
    public User user;
    private String relationship;
    public boolean isPublic;
}
