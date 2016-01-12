package nl.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

/**
 * Beacons are added to the map so they can be assigned to rules
 * @author Hugo van Rijswijk
 */
@Entity(name = "beacon")
public class Beacon implements Serializable {
    
    @Id
    @GenericGenerator(name = "beacon", strategy = "increment")
    @GeneratedValue(generator = "beacon")
    private int id;
    private String title = null;
    private String lat = null;
    private String lng = null;
    private int major;
    private int minor;
    private LocalDateTime dateCreated;

    @OneToMany(mappedBy = "beacon")
    private List<Rule> rules;
    
    public Beacon() {
    }

    public Beacon(String title, String lat, String lng, int major, int minor) {
        super();
        this.title = title;
        this.lat = lat;
        this.lng = lng;
        this.major = major;
        this.minor = minor;
        this.dateCreated = LocalDateTime.now();
    }

    /**
     * Sets the title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the latitude
     *
     * @param lat
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * Sets the longitude
     *
     * @param lng
     */
    public void setLng(String lng) {
        this.lng = lng;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the lat
     */
    public String getLat() {
        return this.lat;
    }

    /**
     * @return the lng
     */
    public String getLng() {
        return this.lng;
    }


    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Beacon{" + "id=" + id + ", title=" + title + ", lat=" + lat + ", lng=" + lng + '}';
    }

    /**
     * @return the rules
     */
    public List<Rule> getRules() {
        return rules;
    }

    /**
     * @param rules the rules to set
     */
    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    /**
     * @return the major id
     */
    public int getMajor() {
       return major;
    }

    /**
     * @param major the major id to set
     */
    public void setMajor(int major) {
       this.major = major;
    }

    /**
     * @return the minor id
     */
    public int getMinor() {
        return minor;
    }

    /**
     * @param minor the minor id to set
     */
    public void setMinor(int minor) {
        this.minor = minor;
    }

    /**
     * @return the date at which the beacon was created
     */
    public LocalDateTime getDate() {
        return dateCreated;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDateTime date) {
        this.dateCreated = date;
    }
}

