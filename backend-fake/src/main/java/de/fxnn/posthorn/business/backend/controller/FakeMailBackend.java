package de.fxnn.posthorn.business.backend.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import de.fxnn.posthorn.business.mail.entity.Mail;
import de.fxnn.posthorn.business.mail.entity.MailId;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;

@Component
public class FakeMailBackend implements MailStorage, MailSender {

  public static final String PREFIX_OF_VALID_MAIL_IDS = "test";

  @Override
  public String getBackendId() {
    return getClass().getName();
  }

  @Override
  public Optional<Mail> loadMail(MailId mailId) {
    if (getBackendId().equals(mailId.getBackendId()) && mailId.getBackendMailId()
        .startsWith(PREFIX_OF_VALID_MAIL_IDS)) {
      Mail mail = new Mail();
      mail.setMailId(mailId);
      mail.setDateTimeOfSending(LocalDateTime.now());
      mail.setDateTimeOfReception(LocalDateTime.now());
      mail.setSenders(asList("from@example.com"));
      mail.setRecipients(asList("to1@example.com", "to2@example.com"));

      return Optional.of(mail);
    }

    return Optional.empty();
  }

  @Override
  public Iterable<MailId> findMailIdsNewerThan(LocalDateTime dateTime) {
    return findAllMailIds();
  }

  @Override
  public Iterable<MailId> findAllMailIds() {
    return asList(mailId(PREFIX_OF_VALID_MAIL_IDS + "123"), mailId(PREFIX_OF_VALID_MAIL_IDS + "456"),
        mailId(PREFIX_OF_VALID_MAIL_IDS + "789"));
  }

  @Override
  public void createDraft(Mail mail) {
    mail.setMailId(mailId("test" + RandomStringUtils.randomNumeric(3)));
  }

  @Override
  public void updateDraft(Mail mail) {
    // we'd save it to a database here
  }

  @Override
  public void sendDraft(String backendMailId) {
    // we'd send it here
  }

  protected MailId mailId(String backendMailId) {
    return new MailId(getBackendId(), backendMailId);
  }
}
