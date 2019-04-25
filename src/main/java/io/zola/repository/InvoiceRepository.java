package io.zola.repository;

import io.zola.repository.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

  Page<Invoice> findAllByInvoiceNumberOrPoNumberOrderByCreatedAt(String invoiceNumber, String poNumber, Pageable pageable);

}
