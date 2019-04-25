package io.zola.service.validator;

import io.zola.Validator;
import io.zola.exception.InvoiceValidationException;
import io.zola.service.model.InvoiceTO;
import org.springframework.stereotype.Component;

@Component
public class InvoiceToValidator implements Validator<InvoiceTO> {

  @Override
  public void validate(InvoiceTO data) {
    if (data.getAmountCents() < 0) {
      throw new InvoiceValidationException("amount cents cannot be negative for invoice" + data.getInvoiceNumber());
    }
  }
}
