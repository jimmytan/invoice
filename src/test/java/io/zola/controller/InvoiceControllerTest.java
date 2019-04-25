package io.zola.controller;


import io.zola.Converter;
import io.zola.CrudService;
import io.zola.controller.model.InvoiceDTO;
import io.zola.service.context.InvoiceSearchContext;
import io.zola.service.model.InvoiceTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private Converter<InvoiceDTO, InvoiceTO> converter;

  @MockBean
  private CrudService<InvoiceTO, InvoiceSearchContext> invoiceService;

  private JacksonTester<InvoiceDTO> invoiceDTOJacksonTester;

  @Test
  public void shouldReturnCreatedInvoiceOnValidCreateInvoiceRequest() {

  }

}
