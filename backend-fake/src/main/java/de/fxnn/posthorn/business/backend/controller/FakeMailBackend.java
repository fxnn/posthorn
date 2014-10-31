package de.fxnn.posthorn.business.backend.controller;

import java.util.Optional;

import de.fxnn.posthorn.business.mail.entity.Mail;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.DateTime;
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
  public Optional<Mail> loadMail(String backendMailId) {
    if (backendMailId.startsWith(PREFIX_OF_VALID_MAIL_IDS)) {
      Mail mail = new Mail();
      mail.setBackendId(getBackendId());
      mail.setBackendMailId(backendMailId);
      mail.setSenders(asList("from@example.com"));
      mail.setRecipients(asList("to1@example.com", "to2@example.com"));

      return Optional.of(mail);
    }

    return Optional.empty();
  }

  @Override
  public Iterable<String> findMailIdsNewerThan(DateTime dateTime) {
    return findAllMailIds();
  }

  @Override
  public Iterable<String> findAllMailIds() {
    return asList(PREFIX_OF_VALID_MAIL_IDS + "123", PREFIX_OF_VALID_MAIL_IDS + "456", PREFIX_OF_VALID_MAIL_IDS + "789");
  }

  @Override
  public void createDraft(Mail mail) {
    mail.setBackendMailId("test" + RandomStringUtils.randomNumeric(3));
    System.out.println(ReflectionToStringBuilder.reflectionToString(mail, ToStringStyle.MULTI_LINE_STYLE)
        .toString());
  }

  @Override
  public void updateDraft(Mail mail) {
    System.out.println(ReflectionToStringBuilder.reflectionToString(mail, ToStringStyle.MULTI_LINE_STYLE)
        .toString());
  }

  @Override
  public void sendDraft(String backendMailId) {
    // TODO Implementieren
  }
}
