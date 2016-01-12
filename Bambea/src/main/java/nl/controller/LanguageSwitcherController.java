package nl.controller;

import java.io.IOException;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Rick
 */
@Controller
public class LanguageSwitcherController {
    
    @RequestMapping(value = "/change-language")
    public ModelAndView home(Locale language, HttpServletRequest request) throws IOException {
        String referer = request.getHeader("Referer");
        return new ModelAndView("redirect:" + referer);
    }
}
