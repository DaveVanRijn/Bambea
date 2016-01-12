package nl.controller;

import java.io.IOException;
import nl.editor.ActionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import nl.model.Action;
import nl.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Dave van Rijn, Student 500714558, Klas IS202
 */
@Controller
@RequestMapping(value = "/action")
public class ActionController {

    @Autowired
    private ActionService actionService;
    @Autowired
    private ActionEditor actionEditor;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Action.class, this.actionEditor);
    }

    @RequestMapping(value = "/list")
    public ModelAndView actionListPage() throws IOException {
        ModelAndView actionListView = new ModelAndView("/actions/actionList");
        actionListView.addObject("actionList", actionService.getActions());
        actionListView.addObject("action", new Action());

        return actionListView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView roleAdd(@ModelAttribute("action") Action action) {

        if (actionService.getAction(action.getId()) != null) {
            actionService.editAction(action);
        } else {
            actionService.addAction(action);
        }
        ModelAndView actionListView = new ModelAndView("redirect:/action/list");

        return actionListView;
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public ModelAndView removeAction(@RequestParam("id") int id) {
        ModelAndView actionListView = new ModelAndView("redirect:/action/list");
        actionService.deleteAction(id);

        return actionListView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void editAction(
            @RequestParam("id") int id,
            @RequestParam("name") String name,
            @RequestParam("vibrate") String vibrate,
            @RequestParam("url") String url,
            @RequestParam("openApp") String openApp,
            @RequestParam("notification") String notification) {

        Action a = new Action(id, name, vibrate, url, openApp, notification);
        actionService.editAction(a);
    }
}
