package io.zola.service.converter;

import io.zola.Converter;
import io.zola.repository.model.Invoice;
import io.zola.service.model.InvoiceTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvoiceToConverter implements Converter<InvoiceTO, Invoice> {

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public Invoice to(InvoiceTO source) {
    Invoice invoice = modelMapper.map(source, Invoice.class);
    return invoice;
  }

  @Override
  public InvoiceTO from(Invoice target) {
    InvoiceTO invoiceTO = modelMapper.map(target, InvoiceTO.class);
    return invoiceTO;
  }
}
