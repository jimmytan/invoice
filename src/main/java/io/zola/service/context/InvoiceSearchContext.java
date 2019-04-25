package io.zola.service.context;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceSearchContext {

  private String invoiceNumber;
  private String poNumber;
  private Pageable pageable;
}
