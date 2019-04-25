package io.zola.service;

import io.zola.Converter;
import io.zola.CrudService;
import io.zola.Validator;
import io.zola.exception.InvoiceException;
import io.zola.repository.InvoiceRepository;
import io.zola.repository.model.Invoice;
import io.zola.service.context.InvoiceSearchContext;
import io.zola.service.model.InvoiceTO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService implements CrudService<InvoiceTO, InvoiceSearchContext> {

  @Autowired
  private InvoiceRepository invoiceRepository;

  @Autowired
  private Converter<InvoiceTO, Invoice> converter;

  @Autowired
  private Validator<InvoiceTO> validator;

  @Override
  public InvoiceTO create(InvoiceTO model) {
    validator.validate(model);
    return Optional.of(model)
        .map(converter::to)
        .map(invoiceRepository::save)
        .map(converter::from)
        .orElseThrow(() -> new InvoiceException("unable to save invoice" + model.toString()));
  }

  @Override
  public Page<InvoiceTO> search(InvoiceSearchContext searchContext) {
    Pageable pageable = searchContext.getPageable();
    if (pageable == null) {
      new InvoiceException("must provide pagination information for search");
    }
    Page<Invoice> invoices;
    if (searchContext.getPoNumber() == null && searchContext.getInvoiceNumber() == null) {
      invoices = invoiceRepository.findAll(pageable);
    }else {
      invoices = invoiceRepository.findAllByInvoiceNumberOrPoNumberOrderByCreatedAt(searchContext.getInvoiceNumber(), searchContext.getPoNumber(), pageable);
    }

    List<InvoiceTO> content = invoices.getContent().stream().map(converter::from).collect(Collectors.toList());
    return new PageImpl(content, pageable, invoices.getTotalElements());
  }
}
