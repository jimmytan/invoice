package io.zola.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InvoiceException extends RuntimeException {

  public InvoiceException(String message) {
    super(message);
  }

}
