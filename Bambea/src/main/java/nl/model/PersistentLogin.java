package nl.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Handles the persistent logins for use with the "Remember Me" function when logging in
 * @author Hugo van Rijswijk
 */
@Entity
@Table(name="persistent_logins")
public class PersistentLogin implements Serializable{
    
   @Column(name="username", nullable=false) 
   private String username;
   @Id
   @Column(name="series", nullable=false) 
   private String series;
   @Column(name="token", nullable=false) 
   private String token;
   @Column(name="last_used", nullable=false) 
   private LocalDateTime lastUsed;
    
   public PersistentLogin(){
       
   }
   
   public PersistentLogin(String username, String series, String token){
       this.username = username;
       this.series = series;
       this.token = token;
       this.lastUsed = LocalDateTime.now();
   }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(LocalDateTime lastUsed) {
        this.lastUsed = LocalDateTime.now();
    }
   
}
