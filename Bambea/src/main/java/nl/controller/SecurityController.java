package nl.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * This controller handles all security related things like logging in and out
 * @author Hugo van Rijswijk
 */
@Controller
public class SecurityController {

    /**
     * The login page. Adds a logout/error message if needed.
     * @param error
     * @param logout
     * @param request
     * @return 
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            HttpServletRequest request) {

        //Adds an error or loggedout message if that event happened to the login page
        ModelAndView model = new ModelAndView("login");
        if (error != null) {
            model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        String targetUrl = getRememberMeTargetUrlFromSession(request);
        System.out.println(targetUrl);
        if(StringUtils.hasText(targetUrl)){
            model.addObject("targetUrl", targetUrl);
            model.addObject("loginUpdate", true);
        }
        return model;
    }

    private String getErrorMessage(HttpServletRequest request, String key) {

        Exception exception = (Exception) request.getSession().getAttribute(key);

        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = "Invalid username and password!";
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else {
            error = "Invalid username and password!";
        }

        return error;
    }

    /**
     * Logs out the user and sends them back to the login page
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
    
    /**
     * Check if user is logged in with Remember Me cookie
     * @return 
     */
    private boolean isRememberMeAuthenticated(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null){
            return false;
        }
        return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
    }
    
    /**
     * Save targetUrl in session
     * @param request 
     */
    private void setRememberMeTargetUrlToSession(HttpServletRequest request){
        HttpSession  session = request.getSession(false);
        if(session != null){
            session.setAttribute("targetUrl", "/admin/update");
        }
    }
    
    private String getRememberMeTargetUrlFromSession(HttpServletRequest request){
        String targetUrl = "";
        HttpSession session = request.getSession(false);
        if(session != null){
            targetUrl = session.getAttribute("targetUrl") == null?""
                    :session.getAttribute("targetUrl").toString();
        }
        return targetUrl;
    }
}
