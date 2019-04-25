package io.zola.service;

import static io.zola.Constants.INVOICE_NUMBER;
import static io.zola.Constants.PO_NUMBER;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import io.zola.config.AppConfig;
import io.zola.repository.InvoiceRepository;
import io.zola.repository.model.Invoice;
import io.zola.service.converter.InvoiceToConverter;
import io.zola.service.model.InvoiceTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {InvoiceService.class, InvoiceToConverter.class, AppConfig.class})
public class InvoiceServiceTest {

  @Autowired
  private InvoiceService invoiceService;

  @MockBean
  private InvoiceRepository invoiceRepository;

  @Test
  public void shouldSaveTest() {
    LocalDate dueDate = LocalDate.of(2019, 4, 24);
    InvoiceTO invoiceTO = InvoiceTO.builder().invoiceNumber(INVOICE_NUMBER).poNumber(PO_NUMBER).amountCents(10000).dueDate(dueDate).build();

    Invoice invoice = Invoice.builder().amountCents(10000).dueDate(dueDate).invoiceNumber(INVOICE_NUMBER).poNumber(PO_NUMBER).build();
    LocalDateTime createAt = LocalDateTime.of(2019, 4, 24, 10, 56, 0);
    Invoice savedInvoice = Invoice.builder().id(1).createdAt(createAt).amountCents(10000).dueDate(dueDate).invoiceNumber(INVOICE_NUMBER).poNumber(PO_NUMBER).build();

    when(invoiceRepository.save(eq(invoice))).thenReturn(savedInvoice);

    InvoiceTO result = invoiceService.create(invoiceTO);

    InvoiceTO expectation = InvoiceTO.builder().id(1).createdAt(createAt).invoiceNumber(INVOICE_NUMBER).poNumber(PO_NUMBER).amountCents(10000).dueDate(dueDate).build();
    assertEquals(expectation, result);
  }


}
