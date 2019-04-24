package io.zola.service;

import io.zola.CrudService;
import io.zola.service.context.InvoiceSearchContext;
import io.zola.service.model.InvoiceTO;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService implements CrudService<InvoiceTO, InvoiceSearchContext> {

  @Override
  public InvoiceTO create(InvoiceTO model) {
    return null;
  }

  @Override
  public List<InvoiceTO> search(InvoiceSearchContext searchContext) {
    return null;
  }
}
