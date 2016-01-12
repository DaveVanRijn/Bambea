package nl.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Dave van Rijn, Student 500714558, Klas IS202
 */
@Entity(name ="action")
public class Action implements Serializable{
    
    @Id
    @GenericGenerator(name = "action", strategy = "increment")
    @GeneratedValue(generator = "action")
    private int id;
    private String name;
    private String url;
    private boolean vibrate;
    private String openApp;
    private String notification;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "action", orphanRemoval = true)
    private List<Rule> rules;
    
    public Action(){
        
    }
    
    public Action(String name){
        this();
        this.name = name;
    }
    
    public Action(int id, String name, String vibrate, String url, String openApp, String notification){
        this.id = id;
        this.name = name;
        if(vibrate.equals("true")){
            this.vibrate = true;
        } else {
            this.vibrate = false;
        }
        this.url = url;
        this.openApp = openApp;
        this.notification = notification;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isVibrate() {
        return vibrate;
    }

    public void setVibrate(boolean vibrate) {
        this.vibrate = vibrate;
    }

    public String getOpenApp() {
        return openApp;
    }

    public void setOpenApp(String openApp) {
        this.openApp = openApp;
    }
    
//    public List<Rule> getRules() {
//        return rules;
//    }
//
//    public void setRules(List<Rule> rules) {
//        this.rules = rules;
//    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }
}

