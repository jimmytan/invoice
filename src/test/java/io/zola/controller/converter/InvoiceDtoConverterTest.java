package io.zola.controller.converter;

import static org.junit.Assert.assertEquals;

import io.zola.config.AppConfig;
import io.zola.controller.model.InvoiceDTO;
import io.zola.service.model.InvoiceTO;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class, InvoiceDtoConverter.class})
public class InvoiceDtoConverterTest {

  private static final String INVOICE_NUMBER = "invoiceNumber";
  private static final String PO_NUMBER = "poNumber";
  @Autowired
  private InvoiceDtoConverter converter;

  @Test
  public void shouldConvertDtoToToTest() {
    InvoiceDTO source = InvoiceDTO.builder().amountInCents(10000).date("1988-10-19").invoiceNumber(INVOICE_NUMBER).poNumber(PO_NUMBER).build();

    InvoiceTO invoiceTO = converter.to(source);

    LocalDate date = LocalDate.of(1988, 10, 19);
    InvoiceTO result = InvoiceTO.builder().amountInCents(10000).date(date).invoiceNumber(INVOICE_NUMBER).poNumber(PO_NUMBER).build();
    assertEquals(result.toString(), invoiceTO.toString());
  }

  @Test
  public void shouldConvertTOTest() {
    LocalDate date = LocalDate.of(1988, 10, 19);
    InvoiceTO invoiceTO = InvoiceTO.builder().amountInCents(10000).date(date).invoiceNumber(INVOICE_NUMBER).poNumber(PO_NUMBER).build();

    InvoiceDTO invoiceDTO = converter.from(invoiceTO);

    InvoiceDTO result = InvoiceDTO.builder().amountInCents(10000).date("1988-10-19").invoiceNumber(INVOICE_NUMBER).poNumber(PO_NUMBER).build();
    assertEquals(result.toString(), invoiceDTO.toString());
  }

}
