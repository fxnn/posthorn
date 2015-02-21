package de.fxnn.posthorn.business.backend.boundary;

import java.util.Collection;
import java.util.Objects;

import de.fxnn.posthorn.business.backend.controller.MailSender;
import de.fxnn.posthorn.business.mail.entity.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

  @Autowired
  protected Collection<MailSender> mailSenders;

  /**
   * The attribute {@link Mail#mailId} SHOULD NOT be set.
   */
  public void createDraft(String backendId, Mail mail) {

    getMailSenderByBackendId(backendId).createDraft(mail);

  }

  /**
   * The attribute {@link Mail#mailId} MUST be set.
   */
  public void updateDraft(Mail mail) {

    getMailSenderByBackendId(mail.getMailId().getBackendId()).updateDraft(mail);

  }

  protected MailSender getMailSenderByBackendId(String backendId) {
    for (MailSender mailSender : mailSenders) {
      if (Objects.equals(backendId, mailSender.getBackendId())) {
        return mailSender;
      }
    }

    throw new IllegalArgumentException("No MailSender found for backendId [" + backendId + "]");
  }

}
