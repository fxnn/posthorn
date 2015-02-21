package de.fxnn.posthorn.business.mail.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MailIdTest {

  @Test
  public void testFromIdString() {

    assertMailIdEqualsUnderIdStringTransformation(getClass().getName(), "abc");
    assertMailIdEqualsUnderIdStringTransformation("abc", "def");

  }

  private void assertMailIdEqualsUnderIdStringTransformation(String backendId, String backendMailId) {
    MailId originalMailId = new MailId(backendId, backendMailId);
    MailId resultMailId = MailId.fromIdString(originalMailId.toIdString());
    assertEquals(originalMailId, resultMailId);
  }

}