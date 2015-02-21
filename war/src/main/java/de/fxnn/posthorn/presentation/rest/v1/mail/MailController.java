package de.fxnn.posthorn.presentation.rest.v1.mail;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.QueryParam;

import com.wordnik.swagger.annotations.ApiOperation;
import de.fxnn.posthorn.business.backend.boundary.MailSenderService;
import de.fxnn.posthorn.business.backend.boundary.MailStorageService;
import de.fxnn.posthorn.business.mail.entity.Mail;
import de.fxnn.posthorn.business.mail.entity.MailId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static de.fxnn.posthorn.technical.RestResponses.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Controller
@RequestMapping("/rest/v1/mail")
public class MailController {

  @Autowired
  MailStorageService mailStorageService;

  @Autowired
  MailSenderService mailSenderService;

  @ApiOperation("List known mail ids")
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ResponseEntity<List<String>> listMailIds() {

    List<String> result = new ArrayList<>();

    mailStorageService.findAllMailIds().forEach(mailId -> result.add(mailId.toIdString()));

    return ResponseEntity.ok(result);

  }

  @ApiOperation("Retrieves meta data of a known mail")
  @RequestMapping(value = "/{mailId:[a-zA-Z0-9.:+_-]+}", method = RequestMethod.GET)
  public ResponseEntity<Mail> getMetaData(@PathVariable String mailId) {

    try {

      final Mail mail = mailStorageService.getMail(MailId.fromIdString(mailId));
      mail.add(linkTo(methodOn(MailController.class).getMetaData(mailId)).withSelfRel());

      return ok(mail);
    } catch (IllegalArgumentException ex) {
      return notFound();
    }

  }

  /** @todo Probably we also need to update metadata of non-drafts (tags, folders, notes etc.) */
  @ApiOperation("Updates meta data of a draft")
  @RequestMapping(value = "/{mailId}", method = RequestMethod.POST)
  public ResponseEntity<Void> updateDraftMetaData(@PathVariable String mailId, @RequestBody Mail mail) {

    try {

      mailSenderService.updateDraft(mail);
      return noContent();

    } catch (IllegalArgumentException ex) {
      return notFound();
    }

  }

  @ApiOperation("Creates a new mail, stored as draft")
  @RequestMapping(value = "/", method = RequestMethod.POST)
  public ResponseEntity<Void> createDraft(@QueryParam("backendId") String backendId, @RequestBody Mail mail) {

    mailSenderService.createDraft(backendId, mail);

    return created(uriTo(methodOn(MailController.class).getMetaData(mail.getMailId().toIdString())));

  }

  // TODO: Delete Draft

}
