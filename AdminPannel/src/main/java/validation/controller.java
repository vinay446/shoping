package validation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class controller {
	
	 /**
     * *
     * Request from web.xml if 404 (page not found) Exception raises
     *
     * @return to custom 404 page 
     */
    @RequestMapping(value = "/404",method=RequestMethod.GET)
    public String error404() {
        return "404";
    }

    /**
     * *
     * Request from web.xml if 500 (Internal Server Error) Exception raises
     *
     * @return to custom 404 page 
     */
    @RequestMapping(value = "/500",method=RequestMethod.GET)
    public String error500() {
            return "500";
    }
    
    /***
     * home page redirection
     * @return
     */
    @RequestMapping(value="/dashboard",method=RequestMethod.GET)
    public String dashboard() {
    	return "dashboard";
    }
    
    
    
}
