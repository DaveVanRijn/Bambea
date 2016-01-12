package nl.model;

import java.io.Serializable;
//import java.lang.ProcessBuilder.Redirect.Type;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Rick
 */
@Entity(name ="rule")
public class Rule implements Serializable {
    
    @Id
    @GenericGenerator(name = "rule", strategy = "increment")
    @GeneratedValue(generator = "rule")
    private int id;
    
    @ManyToOne
    private Beacon beacon;
    
    @ManyToOne
    private Group group;
    
    @ManyToOne
    private Action action;
    
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    
    public Rule () {
    }
    
    public Rule(Beacon beacon, Group group, Action action, String start,
            String end, String sTime, String eTime) {
        this();
        this.beacon = beacon;
        this.group = group;
        this.action = action;
        this.startDate = start;
        this.endDate = end;
        this.startTime = sTime;
        this.endTime = eTime;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Beacon getBeacon() {
        return beacon;
    }
    
    public void setBeacon(Beacon beacon) {
        this.beacon = beacon;
    }
    
    public Action getAction() {
        return action;
    }
    
    public void setAction(Action action) {
        this.action = action;
    }
    
    public Group getGroup() {
        return group;
    }
    
    public void setGroup(Group group) {
        this.group = group;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    
}
