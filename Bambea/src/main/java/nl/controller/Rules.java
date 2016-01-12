package nl.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import nl.editor.RuleEditor;
import nl.model.Rule;
import nl.service.ActionService;
import nl.service.BeaconService;
import nl.service.GroupService;
import nl.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Rick de Jong
 */
@Controller
public class Rules {
    
    @Autowired
    private RuleService ruleService;
    @Autowired
    private RuleEditor ruleEditor;
    @Autowired
    private GroupService groupService;
    @Autowired
    private BeaconService beaconService;
    @Autowired
    private ActionService actionService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Rule.class, this.ruleEditor);
    }

    @RequestMapping(value = "/rules")
    public ModelAndView home() throws IOException {

        ModelAndView ruleView = new ModelAndView("rules/index");
        List<Rule> rules = ruleService.getRules();
        ruleView.addObject("ruleList", rules);
        
        return ruleView;
    }
    
    @RequestMapping(value = "/rules/delete/{id}", method = RequestMethod.GET)
    public ModelAndView verwijder(@PathVariable Integer id) throws IOException {
        ruleService.deleteRule(id);
        
        /*
        ModelAndView ruleView = new ModelAndView("rules/index");
        List<Rule> rules = ruleService.getRules();
        ruleView.addObject("ruleList", rules);
        ruleView.addObject("melding", true);
        ruleView.addObject("meldingtekst", "verwijderd");
        */
        
        return new ModelAndView("redirect:/rules?melding=verwijderd");
    }
    
    @RequestMapping(value = "/rules/add")
    public ModelAndView toevoegen() throws IOException {
        ModelAndView addRuleView = new ModelAndView("rules/addNew");
        addRuleView.addObject("groupList", groupService.getGroups());
        
        return addRuleView;
    }
    
    @RequestMapping(value = "/rules/add/{group}")
    public ModelAndView toevoegenStap2(@PathVariable Integer group) throws IOException {
        ModelAndView addRuleView2 = new ModelAndView("rules/addNewStap2");
        addRuleView2.addObject("gekozenGroup", groupService.getGroup(group));
        addRuleView2.addObject("beaconList", beaconService.getBeacons());
        
        return addRuleView2;
    }
    
    @RequestMapping(value = "/rules/add/{group}/{beacon}")
    public ModelAndView toevoegenStap3(@PathVariable Integer group, @PathVariable Integer beacon) throws IOException {
        ModelAndView addRuleView3 = new ModelAndView("rules/addNewStap3");
        addRuleView3.addObject("gekozenGroup", groupService.getGroup(group));
        addRuleView3.addObject("gekozenBeacon", beaconService.getBeacon(beacon));
        addRuleView3.addObject("actionList", actionService.getActions());
        
        return addRuleView3;
    }
    
    @RequestMapping(value = "/rules/add/{group}/{beacon}/{actie}", method = RequestMethod.GET)
    public ModelAndView toevoegenStap3(@PathVariable Integer group, @PathVariable Integer beacon, @PathVariable Integer actie) throws IOException {
        ModelAndView addRuleViewBevestig = new ModelAndView("rules/addNewBevestig");
        addRuleViewBevestig.addObject("gekozenGroup", groupService.getGroup(group));
        addRuleViewBevestig.addObject("gekozenBeacon", beaconService.getBeacon(beacon));
        addRuleViewBevestig.addObject("gekozenAction", actionService.getAction(actie));
        addRuleViewBevestig.addObject("rule", new Rule());
        
        return addRuleViewBevestig;
    }
    
    @RequestMapping(value = "/rules/add/{group}/{beacon}/{actie}", method = RequestMethod.POST)
    public ModelAndView toevoegActie(@PathVariable Integer group, @PathVariable Integer beacon, @PathVariable Integer actie, HttpServletRequest request) throws IOException { 
        String startDate = request.getParameter("startdate");
        String endDate = request.getParameter("enddate");
        String startTime = request.getParameter("starttime");
        String endTime = request.getParameter("endtime");
        ruleService.addRule(new Rule(beaconService.getBeacon(beacon),groupService.getGroup(group),actionService.getAction(actie), startDate, endDate, startTime, endTime));

        return new ModelAndView("redirect:/rules?melding=toegevoegd");
    }
}
