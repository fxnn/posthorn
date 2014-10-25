package de.fxnn.posthorn.business.backend.boundary;

import java.util.ArrayList;
import java.util.Collection;

import de.fxnn.posthorn.business.backend.controller.MailSender;
import de.fxnn.posthorn.business.mail.entity.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
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

    mailSenderService.createDraft(mail);

    // then exception is thrown

  }

  @Test
  public void testCreateDraft_invokesRightBackend() {

    givenMailWithBackendId(DEFAULT_BACKEND_ID);
    MailSender defaultMailSender = givenMailSenderWithBackendId(DEFAULT_BACKEND_ID);
    MailSender anotherMailSender = givenMailSenderWithBackendId("SomeOtherBackendId");

    mailSenderService.createDraft(mail);

    verify(defaultMailSender, times(1)).createDraft(mail);
    verify(anotherMailSender, never()).createDraft(mail);

  }

  @Test
  public void testCreateDraft_setsIndexMailId() {

    givenMailWithIndexMailId(null);
    MailSender mailSender = givenDefaultMailSender();
    givenMailSenderSetsBackendMailId(mailSender);

    mailSenderService.createDraft(mail);

    assertNotNull(mail.getIndexMailId());

  }

  private void givenMailSenderSetsBackendMailId(MailSender mailSender) {
    Mockito.doAnswer(invocationOnMock -> {
      mail.setBackendMailId("someBackendMailId");
      return null;
    }).when(mailSender).createDraft(mail);
  }

  private MailSender givenDefaultMailSender() {
    return givenMailSenderWithBackendId(DEFAULT_BACKEND_ID);
  }

  private MailSender givenMailSenderWithBackendId(String backendId) {
    MailSender mailSender = mock(MailSender.class);
    when(mailSender.getBackendId()).thenReturn(backendId);

    mailSenders.add(mailSender);
    return mailSender;
  }

  private void givenMailWithBackendId(String backendId) {
    givenMail();
    mail.setBackendMailId(backendId);
  }

  private void givenMailWithIndexMailId(String mailId) {
    givenMail();
    mail.setIndexMailId(mailId);
  }

  private void givenMail() {
    mail = new Mail();
    mail.setBackendId(DEFAULT_BACKEND_ID);
  }

}
