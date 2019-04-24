package io.zola.controller.converter;

import io.zola.Converter;
import io.zola.controller.model.InvoiceDTO;
import io.zola.service.model.InvoiceTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class InvoiceDtoConverter implements Converter<InvoiceDTO, InvoiceTO> {

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public InvoiceTO to(InvoiceDTO source) {
    if (source == null) {
      return null;
    }

    InvoiceTO invoiceTO = modelMapper.map(source, InvoiceTO.class);
    return invoiceTO;
  }

  @Override
  public InvoiceDTO from(InvoiceTO target) {
    if (target == null) {
      return null;
    }

    InvoiceDTO invoiceDTO = modelMapper.map(target, InvoiceDTO.class);
    return invoiceDTO;
  }
}
