
package nl.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

/**
 * The main part of the registration links
 * Every time a registration link 
 * @author Hugo van Rijswijk
 */
@Entity(name="Registration")
public class Registration implements Serializable{
    @Id 
    private String regLink;
    private String userRole;
    private int nuOfRegistrations;
    
    public Registration(){

    }
    
    public Registration(String regLink, int nuOfRegistrations, String userRole){
        this.regLink = regLink;
        this.nuOfRegistrations = nuOfRegistrations;
        this.userRole = userRole;
    }
    
    /**
     * "uses" one registration link. If the max number of registrations is used, return -1. 
     * -1 indicates the Registration object should be removed from the database as it is no longer usable
     * @return 
     */
    public int useRegistration(){
        if(nuOfRegistrations > 1){
            nuOfRegistrations--;
        } else{
            nuOfRegistrations = -1;
        }        
        return nuOfRegistrations;              
    }
    
    public String getRegLink() {
        return regLink;
    }

    public void setRegLink(String regLink) {
        this.regLink = regLink;
    }

    public String getUseRole() {
        return userRole;
    }

    public void setUser_role(String userRole) {
        this.userRole = userRole;
    }

    
    public int getNuOfRegistrations() {
        return nuOfRegistrations;
    }

    public void setNuOfRegistrations(int nuOfRegistrations) {
        this.nuOfRegistrations = nuOfRegistrations;
    }
    
    
}
