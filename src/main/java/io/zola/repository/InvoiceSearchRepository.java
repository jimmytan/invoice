package io.zola.repository;

import io.zola.repository.model.Invoice;
import io.zola.service.context.InvoiceSearchContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.data.domain.Page;

public class InvoiceSearchRepository implements RepositoryPageableSearch<Invoice, InvoiceSearchContext>  {


  @PersistenceContext
  private EntityManager entityManager;



  @Override
  public Page<Invoice> search(InvoiceSearchContext searchContext) {
    return null;
  }
}
