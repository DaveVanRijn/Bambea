package nl.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import nl.model.Beacon;
import nl.model.Group;
import nl.model.Rule;
import nl.service.BeaconService;
import nl.service.GroupService;
import nl.service.RuleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Wies Kueter
 */
@Controller
@RequestMapping(value = "/api")
public class ApiController {

    @Autowired
    private BeaconService beaconService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private RuleService ruleService;

    public ModelAndView home() throws IOException {

        ModelAndView dashboardView = new ModelAndView("dashboard");
        String pageTitle = "Dashboard";
        dashboardView.addObject("pageTitle", pageTitle);

        return dashboardView;
    }

    @RequestMapping(value = "/beacons/all")
    public ModelAndView api() throws IOException {

        ModelAndView apiView = new ModelAndView("api/api");

        List l1 = new LinkedList();
        
        List<Beacon> beaconList = beaconService.getBeacons();

        for (Beacon beacon : beaconList) {
            Beacon beacons = new Beacon();
            
            Map m1 = new LinkedHashMap();
            
            m1.put("id", beacon.getId());
            m1.put("title", beacon.getTitle());
            m1.put("majid", beacon.getMajor());
            m1.put("minid", beacon.getMinor());
            m1.put("lat", beacon.getLat());
            m1.put("lng", beacon.getLng());

            l1.add(m1);
        }

        String jsonString = JSONValue.toJSONString(l1);
        apiView.addObject("beacons", jsonString);

        return apiView;
    }
    
    @RequestMapping(value = "/groups/all")
    public ModelAndView apiGetGroups() throws IOException {

        ModelAndView apiView = new ModelAndView("api/api");

        Map m2 = new HashMap();
        List l1 = new LinkedList();
        
        List<Group> groupList = groupService.getGroups();

        for ( Group group : groupList ) {
            Group groups = new Group();
            
            Map m1 = new LinkedHashMap();
            m1.put("id", group.getId());
            m1.put("name", group.getName());
            m1.put("description", group.getName());

            l1.add(m1);
        }

        String jsonString = JSONValue.toJSONString(l1);
        apiView.addObject("beacons", jsonString);

        return apiView;
    }
    
    // Get new rules
    @RequestMapping(value = "/rules/new")
    public ModelAndView apiGetNewRules() throws IOException {
        
        ModelAndView apiView = new ModelAndView("api/api");

        Map m2 = new HashMap();
        List l1 = new LinkedList();
        
        List<Rule> ruleList = ruleService.getNewRules();

        for ( Rule rule : ruleList ) {
            
            Map m1 = new LinkedHashMap();
            m1.put( "id", rule.getId() );
            m1.put( "action", rule.getAction().getName() );
            m1.put( "vibrate", rule.getAction().isVibrate() );
            m1.put( "notification", rule.getAction().getNotification() );
            m1.put( "openapp", rule.getAction().getOpenApp() );
            m1.put( "openurl", rule.getAction().getUrl() );
            m1.put( "group", rule.getGroup().getName() );
            m1.put( "startTime", rule.getStartTime() );
            m1.put( "endTime", rule.getEndTime() );
            m1.put( "startDate", rule.getStartDate() );
            m1.put( "endDate", rule.getEndDate() );
            
            Map tb = new LinkedHashMap();
            //m1.put("beacon", tb);
                m1.put( "majid", rule.getBeacon().getMajor() );
                m1.put( "minid", rule.getBeacon().getMinor() );

            l1.add(m1);
        }

        String jsonString = JSONValue.toJSONString(l1);
        apiView.addObject("beacons", jsonString);

        return apiView;
    }
    
    // Get all rules
    @RequestMapping(value = "/rules/all")
    public ModelAndView apiGetRules() throws IOException {

        ModelAndView apiView = new ModelAndView("api/api");

        Map m2 = new HashMap();
        List l1 = new LinkedList();
        
        List<Rule> ruleList = ruleService.getRules();

        for ( Rule rule : ruleList ) {
            
            Map m1 = new LinkedHashMap();
            m1.put( "id", rule.getId() );
            m1.put( "action", rule.getAction().getName() );
            m1.put( "vibrate", rule.getAction().isVibrate() );
            m1.put( "notification", rule.getAction().getNotification() );
            m1.put( "openapp", rule.getAction().getOpenApp() );
            m1.put( "openurl", rule.getAction().getUrl() );
            m1.put( "group", rule.getGroup().getName() );
            m1.put( "startTime", rule.getStartTime() );
            m1.put( "endTime", rule.getEndTime() );
            m1.put( "startDate", rule.getStartDate() );
            m1.put( "endDate", rule.getEndDate() );
            
            Map tb = new LinkedHashMap();
            //m1.put("beacon", tb);
                m1.put( "majid", rule.getBeacon().getMajor() );
                m1.put( "minid", rule.getBeacon().getMinor() );

            l1.add(m1);
        }

        String jsonString = JSONValue.toJSONString(l1);
        apiView.addObject("beacons", jsonString);

        return apiView;
    }
    
    // Get all rules
    @RequestMapping(value = "/rules/deleted")
    public ModelAndView apiGetDeletedRules() throws IOException {

        ModelAndView apiView = new ModelAndView("api/api");

        Map m2 = new HashMap();
        List l1 = new LinkedList();
        
        List<Rule> ruleList = ruleService.getRules();

        for ( Rule rule : ruleList ) {
            
            Map m1 = new LinkedHashMap();
            m1.put( "id", rule.getId() );

            l1.add(m1);
        }

        String jsonString = JSONValue.toJSONString(l1);
        apiView.addObject("beacons", jsonString);

        return apiView;
    }
    
    @RequestMapping(value="/beacons/addData")
    public ModelAndView addData() throws IOException {
        return api();
    }
}
