package de.fxnn.posthorn.presentation.angularjs;

import com.mangofactory.swagger.annotations.ApiIgnore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@ApiIgnore
@Controller
@RequestMapping("/")
public class AngularJsController {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView angularJs() {
    ModelAndView modelAndView = new ModelAndView("angularjs");
    modelAndView.addObject("rootPath", "");
    modelAndView.addObject("angularjsWebjarPath", "/webjars/angularjs/1.3.0");
    modelAndView.addObject("bootstrapWebjarPath", "/webjars/bootstrap/3.3.0");

    return modelAndView;
  }

}
