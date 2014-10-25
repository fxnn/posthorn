package de.fxnn.posthorn.business.mail.entity;

import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

import static java.util.Arrays.asList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mail extends ResourceSupport {

  private String mailId;

  private List<String> senders;

  private List<String> recipients;

  private List<String> secondaryRecipients;

  private List<String> hiddenRecipients;

  /**
   * The host who originally sent this mail.
   */
  private String sendingHost;

  /**
   * The hosts who transported the mail, in order.
   */
  private List<String> relayHosts;

  /**
   * The host through which this mail was received.
   * <p/>
   * NOTE that while this mail might have multiple recipient, it may only have on receivingHost.
   */
  private String receivingHost;

  public Mail(String mailId, String addressFrom, String... recipients) {
    this(mailId, asList(addressFrom), asList(recipients), Collections.<String>emptyList(),
        Collections.<String>emptyList(), null, Collections.<String>emptyList(), null);
  }

}
