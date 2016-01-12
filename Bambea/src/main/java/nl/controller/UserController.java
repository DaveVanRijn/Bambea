package nl.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import nl.model.User;
import nl.model.UserRole;
import nl.service.UserRoleService;
import nl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Wies Kueter
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
 
    private static String titelNieuw = "Nieuwe gebruiker";
    private static String titelWijzig = "Wijzig gebruiker";
    
    @RequestMapping(value = "/list")
    public ModelAndView listUsers() throws IOException {
        
        ModelAndView userListView = new ModelAndView("/admin/admin");
        
        Map<String,String> role = new LinkedHashMap<>();
	role.put("ROLE_ADMIN", "Administrator");
	role.put("ROLE_MOD", "Moderator");
	role.put("ROLE_PARTNER", "Partner");
        
        // Pass data to view
        userListView.addObject("roleList", role);
        userListView.addObject("useradd", new User());
        userListView.addObject("userRole", new UserRole());
        userListView.addObject("paginaTitel", titelNieuw);
        
        getUsers( userListView );
        
        return userListView;
    }
    
    @RequestMapping(value = "/remove/{username}", method = RequestMethod.GET)
    public ModelAndView removeUser(@PathVariable String username) {
        //User user = userService.getUser(username);
        userService.deleteUser(username);
        
        return new ModelAndView("redirect:/user/list");
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    
    public ModelAndView addUser(
        HttpServletRequest request,
        @ModelAttribute("useradd") User user, 
        @RequestParam(value="userrole", required=false) String userrole) throws IOException {
        
        if(userService.getUser( user.getUsername() ) == null ) {
            
            String role = request.getParameter("userrole");
            
            UserRole userRole = new UserRole(user, userrole);
            
            userService.addUser(user);
            userRoleService.addUserRole( userRole );
        } else { }
        
        return new ModelAndView("redirect:/user/list");
    }
    
    public void getUsers (ModelAndView view) {
        List<User> users = userService.getUsers();
        
        view.addObject("userList", users);  
    }
}
