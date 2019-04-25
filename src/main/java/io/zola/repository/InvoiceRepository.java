package io.zola.repository;

import io.zola.repository.model.Invoice;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

  List<Invoice> findAllByInvoiceNumberOrPoNumber(String invoiceNumber, String poNumber, Pageable pageable);

  List<Invoice> findAllByInvoiceNumberOrPoNumber(String invoiceNumber, String poNumber);

}
