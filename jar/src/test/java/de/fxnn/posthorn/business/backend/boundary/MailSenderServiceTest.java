package de.fxnn.posthorn.business.backend.boundary;

import java.util.ArrayList;
import java.util.Collection;

import de.fxnn.posthorn.business.backend.controller.MailSender;
import de.fxnn.posthorn.business.mail.entity.Mail;
import de.fxnn.posthorn.business.mail.entity.MailId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MailSenderServiceTest {

  static final String DEFAULT_BACKEND_ID = "TestBackendId";

  @InjectMocks
  MailSenderService mailSenderService;

  @Spy
  protected Collection<MailSender> mailSenders = new ArrayList<>();

  private Mail mail;

  @Test(expected = IllegalArgumentException.class)
  public void testCreateDraft_failsWithoutBackend() {

    givenMail();

    mailSenderService.createDraft(DEFAULT_BACKEND_ID, mail);

    // then exception is thrown

  }

  @Test
  public void testCreateDraft_invokesRightBackend() {

    givenMailWithBackendId(DEFAULT_BACKEND_ID);
    MailSender defaultMailSender = givenMailSenderWithBackendId(DEFAULT_BACKEND_ID);
    MailSender anotherMailSender = givenMailSenderWithBackendId("SomeOtherBackendId");

    mailSenderService.createDraft(DEFAULT_BACKEND_ID, mail);

    verify(defaultMailSender, times(1)).createDraft(mail);
    verify(anotherMailSender, never()).createDraft(mail);

  }

  private MailSender givenMailSenderWithBackendId(String backendId) {
    MailSender mailSender = mock(MailSender.class);
    when(mailSender.getBackendId()).thenReturn(backendId);

    mailSenders.add(mailSender);
    return mailSender;
  }

  private void givenMailWithBackendId(String backendId) {
    givenMail();
    mail.setMailId(new MailId(backendId, null));
  }

  private void givenMail() {
    mail = new Mail();
  }

}
