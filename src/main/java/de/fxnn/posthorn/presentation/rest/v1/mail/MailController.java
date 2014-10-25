package de.fxnn.posthorn.presentation.rest.v1.mail;

import java.net.URI;

import de.fxnn.posthorn.business.mail.entity.Mail;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Controller
@RequestMapping("/v1/mail")
public class MailController {

  @RequestMapping(value = "/id/{mailId}", method = RequestMethod.GET)
  public ResponseEntity<Mail> getMetaData(@PathVariable String mailId) {
    final Mail mail = new Mail(mailId, "from@example.com", "to1@example.com", "to2@example.com");
    mail.add(linkTo(methodOn(MailController.class).getMetaData(mailId)).withSelfRel());

    return ResponseEntity.ok(mail);
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public ResponseEntity<Void> create(@RequestBody Mail mail) {
    System.out.println(ReflectionToStringBuilder.reflectionToString(mail, ToStringStyle.MULTI_LINE_STYLE).toString()); // FIXME Remove System.out

    return ResponseEntity.created(uriTo(mail)).build();
  }

  protected static URI uriTo(Mail mail) {
    return linkTo(methodOn(MailController.class).getMetaData(mail.getMailId())).toUri();
  }

}
