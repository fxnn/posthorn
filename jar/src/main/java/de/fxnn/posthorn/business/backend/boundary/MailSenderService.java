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
   * The attribute {@link Mail#backendId} SHOULD be set.
   */
  public void createDraft(Mail mail) {

    for (MailSender mailSender : mailSenders) {
      if (Objects.equals(mail.getBackendId(), mailSender.getBackendId())) {
        mailSender.createDraft(mail);
        mail.setIndexMailId(mail.getBackendMailId()); // TODO: Later, we will have our own indexMailIds
        return;
      }
    }

    throw new IllegalArgumentException("No MailSender found for backendId [" + mail.getBackendId() + "]");
  }

  /**
   * The attribute {@link Mail#indexMailId} MUST be set.
   * The attributes {@link Mail#backendId}, {@link Mail#backendMailId} SHOULD be set.
   */
  public void updateDraft(Mail mail) {
    for (MailSender mailSender : mailSenders) {
      if (mail.getBackendId().equals(mailSender.getBackendId())) {
        mailSender.updateDraft(mail);
        return;
      }
    }

    throw new IllegalArgumentException("No MailSender found for backendId [" + mail.getBackendId() + "]");
  }

}
