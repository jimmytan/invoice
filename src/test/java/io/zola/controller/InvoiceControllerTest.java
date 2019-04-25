package io.zola.controller;


import static io.zola.Constants.INVOICE_NUMBER;
import static io.zola.Constants.PO_NUMBER;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.zola.CrudService;
import io.zola.config.AppConfig;
import io.zola.controller.converter.InvoiceDtoConverter;
import io.zola.controller.model.InvoiceDTO;
import io.zola.controller.validator.InvoiceDtoValidator;
import io.zola.service.context.InvoiceSearchContext;
import io.zola.service.model.InvoiceTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
@ContextConfiguration(classes = {InvoiceController.class, InvoiceDtoValidator.class, InvoiceDtoConverter.class, AppConfig.class})
public class InvoiceControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private CrudService<InvoiceTO, InvoiceSearchContext> invoiceService;

  private JacksonTester<InvoiceDTO> invoiceDTOJacksonTester;

  private JacksonTester<Page<InvoiceDTO>> invoiceDTOPageJacksonTester;

  @Before
  public void setUp() {
    JacksonTester.initFields(this, objectMapper);
  }

  @Test
  public void shouldReturnCreatedInvoiceOnValidCreateInvoiceRequest() throws Exception{
    InvoiceDTO invoiceDTO = InvoiceDTO.builder().amountCents(10000).dueDate("1988-10-19").invoiceNumber(INVOICE_NUMBER).poNumber(PO_NUMBER).build();
    InvoiceDTO returnedInvoiceDTO = InvoiceDTO.builder().id(1).amountCents(10000).dueDate("1988-10-19").createdAt("2019-04-25T10:49:43Z").invoiceNumber(INVOICE_NUMBER).poNumber(PO_NUMBER).build();
    LocalDate date = LocalDate.of(1988, 10, 19);
    LocalDateTime createAt = LocalDateTime.of(2019, 04, 25, 10, 49, 43);
    InvoiceTO invoiceTO = InvoiceTO.builder().amountCents(10000).dueDate(date).invoiceNumber(INVOICE_NUMBER).poNumber(PO_NUMBER).build();
    InvoiceTO savedInvoiceTO = InvoiceTO.builder().id(1).createdAt(createAt).amountCents(10000).dueDate(date).invoiceNumber(INVOICE_NUMBER).poNumber(PO_NUMBER).build();

    when(invoiceService.create(invoiceTO)).thenReturn(savedInvoiceTO);
    this.mockMvc.perform(post(Routes.INVOICE_CREATE_PATH)
        .content(invoiceDTOJacksonTester.write(invoiceDTO).getJson())
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isCreated()).andExpect(content().string(invoiceDTOJacksonTester.write(returnedInvoiceDTO).getJson()));
  }

  @Test
  public void shouldFailCreateInvoiceRequestWithMissingInfo() throws Exception{
    InvoiceDTO invoiceDTO = InvoiceDTO.builder().amountCents(10000).dueDate("1988-10-19").poNumber(PO_NUMBER).build();
    this.mockMvc.perform(post(Routes.INVOICE_CREATE_PATH)
        .content(invoiceDTOJacksonTester.write(invoiceDTO).getJson())
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnSearchInvoiceResult() throws Exception{
    Pageable pageable = PageRequest.of(0, 20);
    Page<InvoiceTO> searchResult = new PageImpl<>(Collections.emptyList(), pageable, 0);

    InvoiceSearchContext searchContext = InvoiceSearchContext.builder().poNumber("ttttt").pageable(pageable).build();
    when(invoiceService.search(searchContext)).thenReturn(searchResult);

    this.mockMvc.perform(get(Routes.INVOICE_SEARCH_PATH + "?poNumber=ttttt&pageSize=20&pageNumber=0").contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk());
  }

}
