package de.fxnn.posthorn.presentation.swagger;

import com.mangofactory.swagger.annotations.ApiIgnore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@ApiIgnore
@Controller
@RequestMapping("/")
public class SwaggerController {
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView swagger() {
        ModelAndView modelAndView = new ModelAndView("swagger");
        modelAndView.addObject("swaggerWebjarPath", "webjars/swagger-ui/2.0.12/");
        modelAndView.addObject("swaggerBaseUrl", "");
        
        return modelAndView;
    }
    
}
