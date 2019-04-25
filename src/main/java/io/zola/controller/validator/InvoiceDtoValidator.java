package io.zola.controller.validator;

import io.zola.Validator;
import io.zola.controller.model.InvoiceDTO;
import io.zola.exception.InvoiceValidationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.stereotype.Component;

@Component
public class InvoiceDtoValidator implements Validator<InvoiceDTO> {

  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

  @Override
  public void validate(InvoiceDTO data) {
      try {
        DATE_FORMAT.parse(data.getDueDate().trim());
      } catch (ParseException e) {
        throw new InvoiceValidationException( String.format("unable to parse due date (%s) for invoice %s", data.getDueDate(), data.getInvoiceNumber()));
      }
  }
}
