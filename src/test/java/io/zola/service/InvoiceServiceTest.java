package io.zola.service;

import static io.zola.Constants.INVOICE_NUMBER;
import static io.zola.Constants.PO_NUMBER;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import io.zola.config.AppConfig;
import io.zola.exception.InvoiceException;
import io.zola.repository.InvoiceRepository;
import io.zola.repository.model.Invoice;
import io.zola.service.context.InvoiceSearchContext;
import io.zola.service.converter.InvoiceToConverter;
import io.zola.service.model.InvoiceTO;
import io.zola.service.validator.InvoiceToValidator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {InvoiceService.class, InvoiceToConverter.class, AppConfig.class, InvoiceToValidator.class})
public class InvoiceServiceTest {

  @Autowired
  private InvoiceService invoiceService;

  @MockBean
  private InvoiceRepository invoiceRepository;

  @Captor
  private ArgumentCaptor<String> invoiceNumberCaptor;

  @Captor
  private ArgumentCaptor<String> poNumberCaptor;

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

  @Test
  public void shouldSearchWithInvoiceNumberOrPoNumberTest() {
    LocalDate dueDate = LocalDate.of(2019, 4, 24);
    LocalDateTime createAt = LocalDateTime.of(2019, 4, 24, 10, 56, 0);
    Invoice invoice = Invoice.builder().id(1).createdAt(createAt).amountCents(10000).dueDate(dueDate).invoiceNumber(INVOICE_NUMBER).poNumber(PO_NUMBER).build();
    Invoice invoice1= Invoice.builder().id(2).createdAt(createAt).amountCents(20000).dueDate(dueDate).invoiceNumber(INVOICE_NUMBER).poNumber(PO_NUMBER).build();
    Pageable pageable = PageRequest.of(0, 2);
    InvoiceSearchContext invoiceSearchContext = InvoiceSearchContext.builder().invoiceNumber(INVOICE_NUMBER).poNumber(PO_NUMBER).pageable(pageable).build();
    when(invoiceRepository.findAllByInvoiceNumberOrPoNumberOrderByCreatedAtDesc(invoiceNumberCaptor.capture(), poNumberCaptor.capture(), any())).thenReturn(asList(invoice, invoice1));

    List<InvoiceTO> result = invoiceService.search(invoiceSearchContext);

    InvoiceTO expectationTo = InvoiceTO.builder().id(1).createdAt(createAt).invoiceNumber(INVOICE_NUMBER).poNumber(PO_NUMBER).amountCents(10000).dueDate(dueDate).build();
    InvoiceTO expectationTo1 = InvoiceTO.builder().id(2).createdAt(createAt).invoiceNumber(INVOICE_NUMBER).poNumber(PO_NUMBER).amountCents(20000).dueDate(dueDate).build();

    assertEquals(asList(expectationTo, expectationTo1), result);
    assertEquals(INVOICE_NUMBER, invoiceNumberCaptor.getValue());
    assertEquals(PO_NUMBER, poNumberCaptor.getValue());
  }

  @Test
  public void shouldSearcAllhWithoutInvoiceNumberOrPoNumberTest() {
    LocalDate dueDate = LocalDate.of(2019, 4, 24);
    LocalDateTime createAt = LocalDateTime.of(2019, 4, 24, 10, 56, 0);
    Invoice invoice = Invoice.builder().id(1).createdAt(createAt).amountCents(10000).dueDate(dueDate).invoiceNumber(INVOICE_NUMBER).poNumber(PO_NUMBER).build();
    Invoice invoice1= Invoice.builder().id(2).createdAt(createAt).amountCents(20000).dueDate(dueDate).invoiceNumber(INVOICE_NUMBER).poNumber(PO_NUMBER).build();
    Pageable pageable = PageRequest.of(0, 2);
    Page<Invoice> invoicePage = new PageImpl<>(asList(invoice, invoice1), pageable, 4);
    InvoiceSearchContext invoiceSearchContext = InvoiceSearchContext.builder().pageable(pageable).build();
    when(invoiceRepository.findAll(eq(pageable))).thenReturn(invoicePage);

    List<InvoiceTO> result = invoiceService.search(invoiceSearchContext);

    InvoiceTO expectationTo = InvoiceTO.builder().id(1).createdAt(createAt).invoiceNumber(INVOICE_NUMBER).poNumber(PO_NUMBER).amountCents(10000).dueDate(dueDate).build();
    InvoiceTO expectationTo1 = InvoiceTO.builder().id(2).createdAt(createAt).invoiceNumber(INVOICE_NUMBER).poNumber(PO_NUMBER).amountCents(20000).dueDate(dueDate).build();

    assertEquals(asList(expectationTo, expectationTo1), result);
  }

  @Test(expected = InvoiceException.class)
  public void shouldFailSearchWithoutPagination() {
    InvoiceSearchContext invoiceSearchContext = InvoiceSearchContext.builder().invoiceNumber(INVOICE_NUMBER).poNumber(PO_NUMBER).build();
    invoiceService.search(invoiceSearchContext);
  }

}
