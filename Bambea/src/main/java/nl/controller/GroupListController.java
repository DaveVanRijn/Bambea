package nl.controller;

import javax.validation.Valid;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import java.io.IOException;
import nl.editor.GroupEditor;
import nl.model.Group;
import nl.service.GroupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Dave van Rijn, Student 500714558, Klas IS202
 */
@Controller
@RequestMapping(value = "/groups")
public class GroupListController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private GroupEditor groupEditor;

    private static String titelNieuw = "Nieuw groep";
    private static String titelWijzig = "Wijzig groep";

    /*@RequestMapping(value = "/groups")
    public ModelAndView groupList() throws IOException {
        
        ModelAndView groupListView = new ModelAndView("groups/groupList");
        
        //groupListView.addObject("group", group);
        groupListView.addObject("groupList", groupService.getGroups());

        return groupListView;
    }*/
    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public ModelAndView groupAddPage() throws IOException {

        ModelAndView teamAddView = new ModelAndView("/groups/groupList");
        teamAddView.addObject("paginaTitel", titelNieuw);
        teamAddView.addObject("groupadd", new Group());
        Group group = new Group(1, "huis");
        groupService.addGroup(group);

        teamAddView.addObject("groupList", groupService.getGroups());
        
        return teamAddView;
    }

    @RequestMapping(value = "/groups", method = RequestMethod.POST)
    public ModelAndView roleAdd(@ModelAttribute("groupadd") @Valid Group group, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            ModelAndView groupAddView = new ModelAndView("/groups/groupList");
            groupAddView.addObject("group", group);
            return groupAddView;
        } else {
            ModelAndView groupListView = new ModelAndView("/groups/groupList");
            groupService.addGroup(group);
            groupListView.addObject("groupList", groupService.getGroups());

            return groupListView;
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Group.class, this.groupEditor);
    }

    @RequestMapping(value = "/list")
    public ModelAndView groupListPage() throws IOException {

        ModelAndView groupListView = new ModelAndView("groups/groupList");
        groupListView.addObject("groupList", groupService.getGroups());
        groupListView.addObject("group", new Group());
        groupListView.addObject("buttonText", "Add Group");

        return groupListView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView groupAdd(@ModelAttribute("group") Group group) {

        //if (groupService.groupGetById(group.getId()) != null) {
        if (groupService.getGroup(group.getId()) != null) {
            groupService.editGroup(group);
        } else {
            groupService.addGroup(group);
        }

        return new ModelAndView("redirect:/groups/list");
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editGroupPage(@PathVariable int id) {
        Group group = groupService.getGroup(id);
        ModelAndView groupEditView = new ModelAndView("groups/groupList");
        groupEditView.addObject("groupList", groupService.getGroups());
        groupEditView.addObject("group", group);
        groupEditView.addObject("buttonText", "Save Group");

        return groupEditView;
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public ModelAndView removeGroup(@PathVariable int id) {
        groupService.deleteGroup(id);
        
        return new ModelAndView("redirect:/groups/list");
    }
}
