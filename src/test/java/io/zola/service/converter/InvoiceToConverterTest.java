package io.zola.service.converter;

import static io.zola.Constants.INVOICE_NUMBER;
import static io.zola.Constants.PO_NUMBER;
import static org.junit.Assert.assertEquals;

import io.zola.config.AppConfig;
import io.zola.repository.model.Invoice;
import io.zola.service.model.InvoiceTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class, InvoiceToConverter.class})
public class InvoiceToConverterTest {

  @Autowired
  private InvoiceToConverter converter;

  @Test
  public void shouldConvertToDataModelTest() {
    LocalDate dueDate = LocalDate.of(2019, 4, 24);
    InvoiceTO invoiceTO = InvoiceTO.builder().invoiceNumber(INVOICE_NUMBER).poNumber(PO_NUMBER).amountCents(10000).dueDate(dueDate).build();

    Invoice invoice = converter.to(invoiceTO);
    Invoice result = Invoice.builder().amountCents(10000).dueDate(dueDate).invoiceNumber(INVOICE_NUMBER).poNumber(PO_NUMBER).build();
    assertEquals(result, invoice);
  }

  @Test
  public void shouldConvertToTransferObjectTest() {
    LocalDate dueDate = LocalDate.of(2019, 4, 24);
    LocalDateTime createAt = LocalDateTime.of(2019, 4, 24, 10, 56, 0);
    Invoice invoice = Invoice.builder().id(1).invoiceNumber(INVOICE_NUMBER).poNumber(PO_NUMBER).amountCents(10000).dueDate(dueDate).createdAt(createAt).build();

    InvoiceTO invoiceTO = converter.from(invoice);
    InvoiceTO result = InvoiceTO.builder().id(1).createdAt(createAt).amountCents(10000).dueDate(dueDate).invoiceNumber(INVOICE_NUMBER).poNumber(PO_NUMBER).build();
    assertEquals(result, invoiceTO);
  }

}
