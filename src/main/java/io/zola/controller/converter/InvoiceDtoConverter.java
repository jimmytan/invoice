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
  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

  @Override
  public InvoiceTO to(InvoiceDTO source) {
    if (source == null) {
      return null;
    }

    InvoiceTO invoiceTO = modelMapper.map(source, InvoiceTO.class);
    if (source.getDueDate() != null) {
      invoiceTO.setDueDate(LocalDate.parse(source.getDueDate(), DATE_FORMATTER));
    }
    return invoiceTO;
  }

  @Override
  public InvoiceDTO from(InvoiceTO target) {
    if (target == null) {
      return null;
    }

    InvoiceDTO invoiceDTO = modelMapper.map(target, InvoiceDTO.class);
    if (target.getDueDate() != null) {
      invoiceDTO.setDueDate(target.getDueDate().format(DATE_FORMATTER));
    }

    if (target.getCreatedAt() != null) {
      invoiceDTO.setCreatedAt(target.getCreatedAt().format(DATE_TIME_FORMATTER));
    }
    return invoiceDTO;
  }
}
