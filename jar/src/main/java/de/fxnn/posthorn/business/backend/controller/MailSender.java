package de.fxnn.posthorn.business.backend.controller;

import de.fxnn.posthorn.business.mail.entity.Mail;

public interface MailSender extends MailBackend {

  /**
   * Note, that {@link Mail#backendMailId} SHOULD be empty.
   */
  void createDraft(Mail mail);

  /**
   * Note, that {@link Mail#backendMailId} SHOULD NOT be empty.
   */
  void updateDraft(Mail mail);

  void sendDraft(String backendMailId);

}
