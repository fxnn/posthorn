package de.fxnn.posthorn.business.backend.controller;

import de.fxnn.posthorn.business.mail.entity.Mail;

public interface MailSender {

  void storeDraft(Mail mail);

  void sendDraft(String mailId);

}
