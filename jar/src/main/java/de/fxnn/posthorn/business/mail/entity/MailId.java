package de.fxnn.posthorn.business.mail.entity;

import java.util.List;

import com.google.common.base.Splitter;
import lombok.Value;

@Value
public class MailId {

  private final String backendId;

  private final String backendMailId;

  public String toIdString() {
    return backendId + ":" + backendMailId;
  }

  public static MailId fromIdString(String idString) {
    List<String> idStringParts = Splitter.on(":").splitToList(idString);
    if (idStringParts.size() == 2) {
      return new MailId(idStringParts.get(0), idStringParts.get(1));
    }

    throw new IllegalArgumentException("No valid MailId [" + idString + "]");
  }

}
