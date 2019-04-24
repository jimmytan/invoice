package io.zola.service.context;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class InvoiceSearchContext {

  private String invoiceNumber;
  private String poNumber;
}
