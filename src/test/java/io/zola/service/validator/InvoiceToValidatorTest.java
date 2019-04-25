package io.zola.service.validator;

import static io.zola.Constants.INVOICE_NUMBER;
import static io.zola.Constants.PO_NUMBER;

import io.zola.exception.InvoiceValidationException;
import io.zola.service.model.InvoiceTO;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class InvoiceToValidatorTest {

  private InvoiceToValidator validator = new InvoiceToValidator();

  @Test(expected = InvoiceValidationException.class)
  public void shouldFailValidatorNegativeAmount() {
    LocalDate dueDate = LocalDate.of(2019, 4, 24);
    InvoiceTO invoiceTO = InvoiceTO.builder().invoiceNumber(INVOICE_NUMBER).poNumber(PO_NUMBER).amountCents(-10000).dueDate(dueDate).build();

    validator.validate(invoiceTO);
  }

}
