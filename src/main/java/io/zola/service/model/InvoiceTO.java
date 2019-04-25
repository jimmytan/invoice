package io.zola.service.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceTO {

  private long id;
  private String invoiceNumber;
  private String poNumber;
  private long amountInCents;
  private LocalDate dueDate;
  private LocalDateTime createAt;

}
