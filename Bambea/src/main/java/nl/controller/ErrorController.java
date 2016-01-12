package nl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * All error page locations are defined in web.xml, this controller handles viewing the error pages when they are requested
 * @author Hugo van Rijswijk
 */
@Controller
public class ErrorController {

    /**
     * The custom 404 page
     * @return 
     */
    @RequestMapping(value = "/error/404**", method = RequestMethod.GET)
    public ModelAndView pageNotFound() {
        ModelAndView model = new ModelAndView();

        model.setViewName("errors/404");
        
        return model;
    }

    /**
     * The custom 403 error page
     * @return 
     */
    @RequestMapping(value = "/error/403**", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {
        ModelAndView model = new ModelAndView();

        model.setViewName("errors/403");
        
        return model;
    }

    /**
     * The custom 500 error page
     * @return 
     */
    @RequestMapping(value = "/error/500**", method = RequestMethod.GET)
    public ModelAndView serverError() {
        ModelAndView model = new ModelAndView();

        model.setViewName("errors/500");
        
        return model;
    }
}
