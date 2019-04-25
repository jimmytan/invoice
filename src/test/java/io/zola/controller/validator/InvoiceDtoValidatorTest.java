package io.zola.controller.validator;

import static io.zola.Constants.INVOICE_NUMBER;
import static io.zola.Constants.PO_NUMBER;

import io.zola.controller.model.InvoiceDTO;
import io.zola.exception.InvoiceValidationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class InvoiceDtoValidatorTest {

  private InvoiceDtoValidator validator = new InvoiceDtoValidator();

  @Test(expected = InvoiceValidationException.class)
  public void shouldFailValidationWrongFormatDueDate() {
    InvoiceDTO dto = InvoiceDTO.builder().amountCents(10000).dueDate("1988/10/19").invoiceNumber(INVOICE_NUMBER).poNumber(PO_NUMBER).build();
    validator.validate(dto);
  }

}
