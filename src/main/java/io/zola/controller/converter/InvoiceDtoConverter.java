package io.zola.controller.converter;

import io.zola.Converter;
import io.zola.controller.model.InvoiceDTO;
import io.zola.service.model.InvoiceTO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvoiceDtoConverter implements Converter<InvoiceDTO, InvoiceTO> {

  @Autowired
  private ModelMapper modelMapper;

  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  @Override
  public InvoiceTO to(InvoiceDTO source) {
    if (source == null) {
      return null;
    }

    InvoiceTO invoiceTO = modelMapper.map(source, InvoiceTO.class);
    if (source.getDate() != null) {
      invoiceTO.setDate(LocalDate.parse(source.getDate(), DATE_FORMATTER));
    }
    return invoiceTO;
  }

  @Override
  public InvoiceDTO from(InvoiceTO target) {
    if (target == null) {
      return null;
    }

    InvoiceDTO invoiceDTO = modelMapper.map(target, InvoiceDTO.class);
    if (target.getDate() != null) {
      invoiceDTO.setDate(target.getDate().format(DATE_FORMATTER));
    }
    return invoiceDTO;
  }
}
