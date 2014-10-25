package de.fxnn.posthorn.presentation.rest.v1.page;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import de.fxnn.posthorn.business.page.entity.Mail;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/v1/mail")
public class MailController {
    
    @RequestMapping(value = "/{mailId}", method = RequestMethod.GET)
    public HttpEntity<Mail> getMetaData(@PathVariable String mailId) {
        final Mail mail = new Mail(mailId, "from@example.com", "to1@example.com", "to2@example.com");
        mail.add(linkTo(methodOn(MailController.class).getMetaData(mailId)).withSelfRel());
        
        return new ResponseEntity(mail, HttpStatus.OK);
    }
    
}
