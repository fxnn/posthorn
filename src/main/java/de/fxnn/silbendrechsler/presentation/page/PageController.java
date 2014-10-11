package de.fxnn.silbendrechsler.presentation.page;

import com.mangofactory.swagger.annotations.ApiIgnore;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import de.fxnn.silbendrechsler.business.page.entity.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/page")
public class PageController {
    
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public HttpEntity<Page> get(@PathVariable String name) {
        final Page page = new Page(name);
        page.add(linkTo(methodOn(PageController.class).get(name)).withSelfRel());
        
        return new ResponseEntity(page, HttpStatus.OK);
    }
    
}
