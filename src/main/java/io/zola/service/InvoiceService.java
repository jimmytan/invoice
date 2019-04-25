package io.zola.service;

import io.zola.Converter;
import io.zola.CrudService;
import io.zola.exception.InvoiceException;
import io.zola.repository.InvoiceRepository;
import io.zola.repository.model.Invoice;
import io.zola.service.context.InvoiceSearchContext;
import io.zola.service.model.InvoiceTO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService implements CrudService<InvoiceTO, InvoiceSearchContext> {

  @Autowired
  private InvoiceRepository invoiceRepository;

  @Autowired
  private Converter<InvoiceTO, Invoice> converter;

  @Override
  public InvoiceTO create(InvoiceTO model) {
    return Optional.of(model)
        .map(converter::to)
        .map(invoiceRepository::save)
        .map(converter::from)
        .orElseThrow(() -> new InvoiceException("unable to save invoice" + model.toString()));
  }

  @Override
  public List<InvoiceTO> search(InvoiceSearchContext searchContext) {
    Pageable pageable = PageRequest.of(searchContext.getPageNumber(), searchContext.getPageSize(), Sort.by("createdAt"));

    List<Invoice> invoices = invoiceRepository.findAllByInvoiceNumberOrPoNumber(searchContext.getInvoiceNumber(), searchContext.getPoNumber(), pageable);
    return invoices.stream().map(converter::from).collect(Collectors.toList());
  }
}
