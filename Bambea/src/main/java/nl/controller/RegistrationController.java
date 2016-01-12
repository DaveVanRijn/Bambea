package nl.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import nl.model.Registration;
import nl.model.User;
import nl.model.UserRole;
import nl.service.RegistrationService;
import nl.service.UserRoleService;
import nl.service.UserService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Hugo van Rijswijk
 */
@Controller
@RequestMapping(value = "/")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    JSONParser parser = new JSONParser();

    @RequestMapping(value = "/registration/{regLink}**", method = RequestMethod.GET)
    public ModelAndView register(@PathVariable String regLink) {
        Registration reg = registrationService.getRegistration(regLink);
        if (reg == null) {
            return new ModelAndView("redirect:/error/404");
        }
        ModelAndView registrationView = new ModelAndView("/registration/register");
        registrationView.addObject("registrationID", regLink);
        registrationView.addObject("userRegister", new User());

        return registrationView;
    }

    @RequestMapping(value = "/registration/add/{regLink}")
    public ModelAndView registerUser(@ModelAttribute("userRegister") User user,
            @PathVariable String regLink) {
        Registration reg = registrationService.getRegistration(regLink);
        if(reg == null){
            return new ModelAndView("redirect:/error/404");
        }
        if (userService.getUser(user.getUsername()) == null) {
            String role = reg.getUseRole();
            UserRole userRole = new UserRole(user, role);

            userService.addUser(user);
            userRoleService.addUserRole(userRole);

            registrationService.useRegistration(reg.getRegLink());
            return new ModelAndView("/registration/succes");
        }else{
            ModelAndView register = new ModelAndView("redirect:/registration/"+regLink);
            String failmsg = "accountExists";
            register.addObject("failmsg", failmsg);
            return register;
        }
        
    }
    
        @RequestMapping(value = "/registration/success**", method = RequestMethod.GET)
    public ModelAndView registerSuccess(HttpServletRequest request) {
        ModelAndView mod = new ModelAndView("/registration/succes");
        return mod;
    }

    @RequestMapping(value = "/registration/linkGen", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8", produces = "text/plain")
    public @ResponseBody
    String generateLink(HttpServletRequest request, @RequestBody String json) throws ParseException {
        //Parses the json object recieved from the webpage.
        Object obj = parser.parse(json);
        JSONArray array = new JSONArray();
        array.add(obj);
        JSONObject o = (JSONObject) array.get(0);

        //Gets the values's from the parsed JSON string
        String regLink = (String) o.get("regLink").toString();
        int nuOfRegistrations = Integer.valueOf(o.get("nuOfRegistrations").toString());
        String userRole = (String) o.get("userRole").toString();
        if(regLink.isEmpty()){
            regLink = Integer.toHexString((int)(Math.random()*1600000));
        }

        System.out.println(regLink + nuOfRegistrations + userRole);
        Registration reg = new Registration(regLink, nuOfRegistrations, userRole);

        registrationService.addRegistration(reg);
        String url = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath())
                + "/registration/" + reg.getRegLink();
        return url;
    }
    


}
