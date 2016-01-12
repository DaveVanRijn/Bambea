package nl.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import nl.model.Action;
import nl.model.Beacon;
import nl.model.Group;
import nl.model.Rule;
import nl.model.User;
import nl.model.UserRole;
import nl.service.ActionService;
import nl.service.BeaconService;
import nl.service.GroupService;
import nl.service.RuleService;
import nl.service.UserRoleService;
import nl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author wieskueter.com
 */
@Controller
public class SetupController {
    
    @Autowired
    private BeaconService beaconService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private RuleService ruleService;
    @Autowired
    private ActionService actionService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserService userService;
    
    private List<Action> actionList = new ArrayList<>();
    private List<Beacon> beaconList = new ArrayList<>();
    private List<Group> groupList = new ArrayList<>();
    
    @RequestMapping(value="/setup")
    public ModelAndView setupMain() throws IOException {
        ModelAndView setupView = new ModelAndView("setup");
        
        addActions();
        addGroups();
        addBeacons();
        addRules();
//        addRoles();
        
        return setupView;
    }
    
    public void addRoles() {
        
        User user = new User();
        user.setUsername("admin");
        user.setPassword("1234");
        
        UserRole userRole = new UserRole(user, "ROLE_ADMIN");
        userService.addUser(user);
        userRoleService.addUserRole( userRole );
    }
    
    public void addGroups() {
      
        Group group = new Group();
        
        group.setId(0);
        group.setName("Travelers Group");
        group.setDescription("Group for travelers, backpackers and travel enthusiasts.");
        
        groupList.add(group);
        groupService.storeAllGroups(groupList);
    }
    
    public void addActions() {
        
        Action action = new Action();
        action.setId(0);
        action.setName("Show message when near tourist attraction");
        action.setVibrate(true);
        action.setUrl("http://www.wieskueter.com/");
        action.setOpenApp("com.whatsapp");
        action.setNotification("You are near a point of interest!");
        
        actionList.add(action);
        
        actionService.storeAllActions(actionList);
    }
    
    public void addRules() {
        List<Rule> ruleList = new ArrayList<>();
        Rule rule = new Rule();
        
        rule.setBeacon( beaconList.get(0) );
        rule.setAction( actionList.get(0) );
        rule.setGroup( groupList.get(0) );
        
        ruleList.add( rule );
        
        ruleService.storeAllRules(ruleList);
    }
    
    public void addBeacons(){
        int i = 1000;
        
        beaconList.add( new Beacon("Kattenburgerstraat 7", "52.372405","4.910916", 4 , 27 ));
        beaconList.add( new Beacon("Kattenburgerstraat 7", "52.372405","4.910916", 4 , 29 ));
        beaconList.add( new Beacon("Kattenburgerstraat 7", "52.376772","4.904265" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("C. van Eesterenlaan", "52.376585","4.904756" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("C. van Eesterenlaan", "52.376772","4.904265" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Knsm-Laan", "52.37261","4.91115" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Knsm-Laan", "52.372865","4.911399" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Knsm-Laan", "52.373174","4.911554" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Knsm-Laan", "52.373419","4.911736"  ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Oostelijke Handelskade", "52.373693","4.91187" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Oostelijke Handelskade", "52.374332","4.912111" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Oostelijke Handelskade", "52.374668","4.911958" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Oostelijke Handelskade", "52.374115","4.912514" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Oostelijke Handelskade", "52.373179","4.912602" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Johan van Hasseltweg", "52.372649","4.91246" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Johan van Hasseltweg", "52.372109","4.912188" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Johan van Hasseltweg", "52.371607","4.912287" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Johan van Hasseltweg", "52.371313","4.912538" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Johan van Hasseltweg", "52.370991","4.913333" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Tosaristraat", "52.371385","4.915376" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Tosaristraat", "52.371643","4.915742" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Tosaristraat", "52.378956","4.89845" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Tosaristraat", "52.378771","4.898911" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Tosaristraat", "52.378295","4.899094" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Azartplein to nr 12", "52.378388","4.899221" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Azartplein to nr 12", "52.378011","4.900502" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Azartplein to nr 12", "52.377724","4.901513" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Surinamekade 1 ", "52.376228","4.905995" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Surinamekade 1 ", "52.375914","4.907075" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Surinamekade 1 ", "52.375687","4.907725" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Ijplein", "52.375504","4.908443" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Buiksloterweg", "52.375399","4.908812" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Buiksloterweg", "52.375278","4.90986" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Kattenburgerstraat 7", "52.374676","4.910788" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Kattenburgerstraat 7", "52.374694","4.912566" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Kattenburgerstraat 7", "52.37277646","4.91838023" , (int) (Math.random() * i), (int) (Math.random() * i)) );
        beaconList.add( new Beacon("Kattenburgerstraat 158", "52.37305411","4.91938466" , (int) (Math.random() * i), (int) (Math.random() * i)) );
        beaconList.add( new Beacon("De Ruyterkade", "52.38079484","4.89892606" , (int) (Math.random() * i), (int) (Math.random() * i)) );
        beaconList.add( new Beacon("Buiksloterweg", "52.38220352","4.90312065" , (int) (Math.random() * i), (int) (Math.random() * i)) );
        beaconList.add( new Beacon("De Ruijterkade", "52.37927386","4.90310469"  , (int) (Math.random() * i), (int) (Math.random() * i)) );
        beaconList.add( new Beacon("Ijplein", "52.38178968","4.90825049" , (int) (Math.random() * i), (int) (Math.random() * i)) );
        beaconList.add( new Beacon("Piet Heinkade Muziekgebouw", "52.37724189","4.91261875" , (int) (Math.random() * i), (int) (Math.random() * i)) );
        beaconList.add( new Beacon("Piet Heinkade Muziekgebouw", "52.37722351","4.91352874"  , (int) (Math.random() * i), (int) (Math.random() * i)) );
        beaconList.add( new Beacon("Kattenburgerstraat", "52.37654475","4.92196912" , (int) (Math.random() * i), (int) (Math.random() * i)) );
        beaconList.add( new Beacon("Tosaristraat", "52.37934518","4.92296523" , (int) (Math.random() * i), (int) (Math.random() * i)) );
        beaconList.add( new Beacon("Sumatrakade", "52.37906524","4.92940386" , (int) (Math.random() * i), (int) (Math.random() * i)) );
        beaconList.add( new Beacon("Surinamekade 1 ", "52.37781755","4.93736532" , (int) (Math.random() * i), (int) (Math.random() * i)) );
        beaconList.add( new Beacon("Knsm-Laan t/o nr. 579-629", "52.37715855","4.94246909" , (int) (Math.random() * i), (int) (Math.random() * i)) );
        beaconList.add( new Beacon("Knsm-Laan nr. 708-722", "52.37705877","4.94606813" , (int) (Math.random() * i), (int) (Math.random() * i)) );
        beaconList.add( new Beacon("Azartplein to nr 12", "52.37676508","4.93740498", (int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Johan van Hasseltweg", "52.38477984","4.93078092" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Oostelijke Handelskade nr. 1079-1213", "52.37360362","4.93905089" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("Rietlandpark", "52.37222681","4.93735574" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("C. van Eesterenlaan nr 41", "52.37204058","4.93868561" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        beaconList.add( new Beacon("C. van Eesterenlaan 313", "52.37090142","4.93865414" ,(int)(Math.random()*i), (int)(Math.random()*i)) );
        
        beaconService.storeAllBeacons(beaconList);
    }
}
