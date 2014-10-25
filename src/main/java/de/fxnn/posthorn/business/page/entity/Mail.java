package de.fxnn.posthorn.business.page.entity;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import static java.util.Arrays.asList;

@Data
public class Mail extends ResourceSupport {

  private final String mailId;

  private final List<String> addressesFrom;

  private final List<String> addressesTo;

  private final List<String> addressesCc;

  private final List<String> addressesBcc;

  public Mail(String mailId, String addressFrom, String ... addressesTo) {
    this(mailId, asList(addressFrom), asList(addressesTo), Collections.<String>emptyList(), Collections.<String>emptyList());
  }

  @JsonCreator
  public Mail(@JsonProperty String mailId, @JsonProperty List<String> addressesFrom, @JsonProperty List<String> addressesTo,
      @JsonProperty List<String> addressesCc, @JsonProperty List<String> addressesBcc) {
    this.mailId = mailId;
    this.addressesFrom = addressesFrom;
    this.addressesTo = addressesTo;
    this.addressesCc = addressesCc;
    this.addressesBcc = addressesBcc;
  }

}
