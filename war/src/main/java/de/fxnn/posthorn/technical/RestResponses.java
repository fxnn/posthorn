package de.fxnn.posthorn.technical;

import java.net.URI;

import org.springframework.http.ResponseEntity;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class RestResponses {

  private RestResponses() {
    // static utility class
  }

  public static <T> ResponseEntity<T> ok(T body) {
    return ResponseEntity.ok(body);
  }

  public static ResponseEntity<Void> created(URI uri) {
    return ResponseEntity.created(uri).build();
  }

  public static ResponseEntity<Void> noContent() {
    return ResponseEntity.noContent().build();
  }

  public static <T> ResponseEntity<T> notFound() {
    return (ResponseEntity) ResponseEntity.notFound().build();
  }

  /**
   * cf. {@link org.springframework.hateoas.mvc.ControllerLinkBuilder#linkTo(Object)}
   */
  public static URI uriTo(Object invocationValue) {
    return linkTo(invocationValue).toUri();
  }

}
